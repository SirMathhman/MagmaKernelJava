package com.meti.parse;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeclareParser implements Parser {
	private final Accumulator accumulator;
	private final Scope scope;

	@Inject
	public DeclareParser(Scope scope, Accumulator accumulator) {
		this.scope = scope;
		this.accumulator = accumulator;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.contains(":")) {
			if (content.contains("=")) {
				int s = IntStream.range(0, content.length())
						.filter(value -> '=' == content.charAt(value))
						.filter(value -> {
							String result = content.substring(value, value + 2);
							return !"=>".equals(result);
						})
						.findFirst()
						.orElseThrow();
				String first = content.substring(0, s).trim();
				int colon = first.indexOf(':');
				String before = first.substring(0, colon).trim();
				String after = first.substring(colon + 1);
				int last = before.lastIndexOf(' ');
				checkKeys(first, before.substring(0, last));
				String name = before.substring(last + 1);
				String typeString = after.trim();
				Instance type = compiler.resolveName(typeString);
				scope.define(name, type);
				accumulator.setString(name);
				accumulator.setInstance(type);
				Node init = compiler.parse(content.substring(s + 1).trim());
				return Optional.of(new DeclareNode(type, name, init));
			} else {
				String first = content;
				int colon = first.indexOf(':');
				String before = first.substring(0, colon).trim();
				String after = first.substring(colon + 1);
				int last = before.lastIndexOf(' ');
				checkKeys(first, before.substring(0, last));
				String name = before.substring(last + 1);
				String typeString = after.trim();
				Instance type = compiler.resolveName(typeString);
				scope.define(name, type);
				return Optional.of(new DeclareNode(type, name, null));
			}
		}
		return Optional.empty();
	}

	private void checkKeys(String content, String keyString) {
		List<DeclareKeys> keys = parseKeys(keyString);
		if (!keys.contains(DeclareKeys.VAL) && !keys.contains(DeclareKeys.VAR)) {
			throw new IllegalArgumentException("Cannot determine mutability: " + content);
		}
	}

	private List<DeclareKeys> parseKeys(String keyString) {
		return Arrays.stream(keyString.split(" "))
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(String::toUpperCase)
				.map(DeclareKeys::valueOf)
				.collect(Collectors.toList());
	}
}
