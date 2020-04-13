package com.meti;

import com.google.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeclareParser implements Parser {
	private final Register register;

	@Inject
	public DeclareParser(Register register) {
		this.register = register;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int equals = content.indexOf('=');
		if (-1 != equals) {
			String before = content.substring(0, equals);
			String after = content.substring(equals + 1);
			int colon = before.indexOf(':');
			String keyString = before.substring(0, colon);
			String typeString = before.substring(colon + 1);
			int lastSpace = keyString.lastIndexOf(' ');
			List<DeclareKey> keys = Arrays.stream(keyString.substring(0, lastSpace).split(" "))
					.filter(s -> !s.isBlank())
					.map(this::parseKey)
					.flatMap(Optional::stream)
					.collect(Collectors.toList());
			if (keys.contains(DeclareKey.VAL) || keys.contains(DeclareKey.VAR)) {
				String nameString = keyString.substring(lastSpace + 1);
				register.set("assigning", true);
				Node result = compiler.parse(after);
				Type type = compiler.resolveName(typeString);
				return Optional.of(new DeclareNode(nameString, type, result));
			}
		}
		return Optional.empty();
	}

	private Optional<DeclareKey> parseKey(String s) {
		try {
			return Optional.of(DeclareKey.valueOf(s));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
