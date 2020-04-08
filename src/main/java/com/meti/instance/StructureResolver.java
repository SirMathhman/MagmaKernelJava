package com.meti.instance;

import com.meti.Compiler;
import com.meti.Instance;
import com.meti.Resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StructureResolver implements Resolver {
	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		if (content.contains("(") || content.contains("=>")) {
			Instance returnType = VoidInstance.INSTANCE;
			List<Instance> parameters = new ArrayList<>();
			int index = 0;
			if (content.startsWith("(")) {
				if (!content.contains(")")) {
					throw new IllegalArgumentException("No ending in parameters: " + content);
				}
				index = findIndex(content);
				List<Instance> collect = parse(compiler, content.substring(1, index));
				parameters.addAll(collect);
			}
			String substring = content.substring(index + 1).trim();
			if (substring.startsWith("=>")) {
				String type = substring.substring(2).trim();
				returnType = compiler.resolveName(type);
			}
			return Optional.of(new StructureInstance(returnType, parameters));
		}
		return Optional.empty();
	}

	private int findIndex(String content) {
		int index = 0;
		int depth = 0;
		char[] charArray = content.substring(1).toCharArray();
		int length = charArray.length;
		for (int i = 0; i < length; i++) {
			char c = charArray[i];
			if (')' == c && 0 == depth) {
				index = i;
			} else {
				if ('(' == c) depth++;
				if (')' == c) depth--;
			}
		}
		return index + 1;
	}

	private List<Instance> parse(Compiler compiler, String value) {
		List<String> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		int depth = 0;
		for (char c : value.toCharArray()) {
			if (',' == c && 0 == depth) {
				list.add(builder.toString());
				builder = new StringBuilder();
			} else {
				if ('(' == c) depth++;
				if (')' == c) depth--;
				builder.append(c);
			}
		}
		list.add(builder.toString());
		return list.stream()
				.filter(s -> !s.isBlank())
				.map(compiler::resolveName)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
