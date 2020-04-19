package com.meti.compile;

import com.google.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

public class BlockParser implements Parser {
	private final Cache cache;
	private final Headers headers;
	private final List<String> lambdas = new ArrayList<>();
	private int counter = 0;

	@Inject
	public BlockParser(Cache cache, Headers headers) {
		this.cache = cache;
		this.headers = headers;
	}

	//("Hello World");
	//(x : Int, y : Int) => Int : {return x + y;}
	//{return 10;}
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("(") && !content.endsWith(")")) {
			int index = findParamEnd(content);
			Map<String, Type> parameters = new HashMap<>();
			if (index != -1) {
				String paramString = content.substring(1, index);
				List<String> paramStrings = new ArrayList<>();
				StringBuilder builder = new StringBuilder();
				int depth = 0;
				for (char c : paramString.toCharArray()) {
					if (c == ',' && depth == 0) {
						paramStrings.add(builder.toString());
						builder = new StringBuilder();
					} else {
						if ('(' == c) depth++;
						if (')' == c) depth--;
						builder.append(c);
					}
				}
				Map<String, Type> map = paramStrings.stream()
						.filter(s -> !s.isBlank())
						.map(String::trim)
						.collect(Collectors.toMap(this::parseParamName, s -> parseParamValue(compiler, s)));
				parameters.putAll(map);
			}
			//=> Int : {return x + y;}
			Type returnType;
			String result = content.substring(index + 1).trim();
			if (result.startsWith("=>")) {
				index = result.indexOf(':');
				String returnName = result.substring(2, index);
				returnType = compiler.resolveName(returnName);
			} else {
				returnType = VoidType.INSTANCE;
			}
			String block = result.substring(index);
			Node node = compiler.parse(block);
			String name = cache.pullName().orElse(lambda());
			Node function = new Function(name, parameters, returnType, node);
			headers.append(2, function.render());
			return Optional.of(new EmptyNode());
		}
		if (content.startsWith("{") && content.endsWith("}")) {
			String result = content.substring(1, content.length() - 1);
			List<String> lines = new ArrayList<>();
			StringBuilder builder = new StringBuilder();
			int depth = 0;
			for (char c : result.toCharArray()) {
				if (';' == c && 0 == depth) {
					lines.add(builder.toString());
					builder = new StringBuilder();
				} else {
					if ('{' == c) depth++;
					if ('}' == c) depth--;
					builder.append(c);
				}
			}
			lines.add(builder.toString());
			List<Node> children = lines.stream()
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(compiler::parse)
					.collect(Collectors.toList());
			return Optional.of(new BlockNode(children));
		}
		return Optional.empty();
	}

	private int findParamEnd(String content) {
		int index = -1;
		int depth = 0;
		char[] charArray = content.toCharArray();
		for (int i = 1; i < charArray.length; i++) {
			char c = charArray[i];
			if (')' == c && 0 == depth) {
				index = i;
				break;
			} else {
				if ('(' == c) depth++;
				if (')' == c) depth--;
			}
		}
		return index;
	}

	String parseParamName(String s) {
		int colon = s.indexOf(':');
		return s.substring(0, colon);
	}

	Type parseParamValue(Compiler compiler, String s) {
		int colon = s.indexOf(':');
		String value = s.substring(colon + 1);
		return compiler.resolveName(value);
	}

	private String lambda() {
		String s = "_lambda" + counter;
		counter++;
		return s;
	}

	private static final class BlockNode implements Node {
		private final Collection<Node> children;

		private BlockNode(Collection<Node> children) {
			this.children = children;
		}

		@Override
		public String render() {
			return children.stream()
					.map(Node::render)
					.collect(Collectors.joining("", "{", "}"));
		}
	}
}
