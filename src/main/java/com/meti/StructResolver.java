package com.meti;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StructResolver implements Resolver {
	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		if (content.startsWith("(") && content.contains("=>")) {
			int returnIndex = content.indexOf("=>");
			String before = content.substring(0, returnIndex).trim();
			String after = content.substring(returnIndex + 2);
			Map<String, Type> arguments = Arrays.stream(before.substring(1, before.length() - 1)
					.split(","))
					.filter(s -> !s.isBlank())
					.collect(Collectors.toMap(s -> {
						int colon = getColon(s);
						return s.substring(0, colon);
					}, s -> {
						int colon = getColon(s);
						String string = s.substring(colon + 1);
						return compiler.resolveName(string.trim());
					}));
			Type returnType = compiler.resolveName(after.trim());
			return Optional.of(new MappedStructType(returnType, arguments));
		}
		return Optional.empty();
	}

	private int getColon(String s) {
		int index = s.indexOf(':');
		if (-1 == index) throw new IllegalArgumentException("Invalid argument format: " + s);
		return index;
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
