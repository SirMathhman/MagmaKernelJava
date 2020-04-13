package com.meti;

import com.google.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeclareParser implements Parser {
	private final Stack stack;
	private final Register register;

	@Inject
	public DeclareParser(Stack stack, Register register) {
		this.stack = stack;
		this.register = register;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int equals = -1;
		char[] charArray = content.toCharArray();
		for (int i = 0; i < charArray.length - 2; i++) {
			String substring = content.substring(i, i + 2);
			if (substring.startsWith("=") && !substring.equals("=>")) {
				equals = i;
				break;
			}
		}
		if (-1 != equals) {
			String before = content.substring(0, equals);
			String after = content.substring(equals + 1).trim();
			int colon = before.indexOf(':');
			String keyString = before.substring(0, colon).trim();
			String typeString = before.substring(colon + 1);
			int lastSpace = keyString.lastIndexOf(' ');
			List<DeclareKey> keys = Arrays.stream(keyString.substring(0, lastSpace).split(" "))
					.filter(s -> !s.isBlank())
					.map(this::parseKey)
					.flatMap(Optional::stream)
					.collect(Collectors.toList());
			if (keys.contains(DeclareKey.VAL) || keys.contains(DeclareKey.VAR)) {
				String nameString = keyString.substring(lastSpace + 1).trim();
				register.set("assigning", true);
				Type type = compiler.resolveName(typeString.trim());
				stack.enter(nameString, type);
				Node result = compiler.parse(after);
				return Optional.of(new DeclareNode(nameString, type, result));
			}
		}
		return Optional.empty();
	}

	private Optional<DeclareKey> parseKey(String s) {
		try {
			return Optional.of(DeclareKey.valueOf(s.toUpperCase()));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
