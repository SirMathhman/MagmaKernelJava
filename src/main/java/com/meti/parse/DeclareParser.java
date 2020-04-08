package com.meti.parse;

import com.meti.Compiler;
import com.meti.Instance;
import com.meti.Node;
import com.meti.Parser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeclareParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.contains(":")) {
			int colon = content.indexOf(':');
			String before = content.substring(0, colon).trim();
			String after = content.substring(colon + 1);
			int last = before.lastIndexOf(' ');
			checkKeys(content, before.substring(0, last));
			String name = before.substring(last + 1);
			String typeString = after.trim();
			Instance type = compiler.resolveName(typeString);
			return Optional.of(new DeclareNode(type, name));
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
