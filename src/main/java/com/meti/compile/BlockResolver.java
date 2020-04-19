package com.meti.compile;

import com.google.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

public class BlockResolver implements Resolver {
	private final Stack stack;

	@Inject
	public BlockResolver(Stack stack) {
		this.stack = stack;
	}

	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		//(x : Int) => Int
		List<Type> parameters = new ArrayList<>();
		Type returnType;
		if (content.startsWith("(")) {
			int index = findParamEnd(content);
			String paramString = content.substring(1, index).trim();
			parameters.addAll(parseParameters(paramString, compiler));
			String returnString = content.substring(index + 1).trim();
			if (returnString.startsWith("=>")) {
				String returnName = returnString.substring(2).trim();
				returnType = compiler.resolveName(returnName);
			} else {
				returnType = VoidType.INSTANCE;
			}
		} else {
			if (content.startsWith("=>")) {
				String returnString = content.substring(2).trim();
				returnType = compiler.resolveName(returnString);
			} else {
				return Optional.empty();
			}
		}
		return Optional.of(new BlockType(parameters, returnType));
	}

	private int findParamEnd(String content) {
		char[] charArray = content.toCharArray();
		int index = -1;
		int depth = 0;
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

	private List<Type> parseParameters(String paramString, Compiler compiler) {
		List<String> paramStrings = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		int depth = 0;
		char[] charArray = paramString.toCharArray();
		for (char c : charArray) {
			if (',' == c && depth == 0) {
				paramStrings.add(builder.toString());
				builder = new StringBuilder();
			} else {
				if ('(' == c) depth++;
				if (')' == c) depth--;
				builder.append(c);
			}
		}
		paramStrings.add(builder.toString());
		return paramStrings.stream()
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(compiler::resolveName)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
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
				paramStrings.add(builder.toString());
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
				int colon = result.indexOf(':');
				String returnName = result.substring(2, colon).trim();
				returnType = compiler.resolveName(returnName);
			} else {
				returnType = VoidType.INSTANCE;
			}
			return Optional.of(new BlockType(parameters.values(), returnType));
		} else {
			return Optional.empty();
		}
	}

	private String parseParamName(String s) {
		int colon = s.indexOf(':');
		return s.substring(0, colon).trim();
	}

	private Type parseParamValue(Compiler compiler, String s) {
		int colon = s.indexOf(':');
		String value = s.substring(colon + 1).trim();
		return compiler.resolveName(value);
	}
}
