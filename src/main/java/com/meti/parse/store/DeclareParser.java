package com.meti.parse.store;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.data.Register;
import com.meti.data.Stack;
import com.meti.parse.Node;
import com.meti.parse.Parser;
import com.meti.parse.Type;
import com.meti.parse.util.EmptyNode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeclareParser implements Parser {
	private final Register register;
	private final Stack stack;

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
			List<DeclareKey> keys = parseKeys(keyString, lastSpace);
			if (keys.contains(DeclareKey.VAL) || keys.contains(DeclareKey.VAR)) {
				String nameString = keyString.substring(lastSpace + 1).trim();
				return set(compiler, typeString, nameString, after);
			}
		} else {
			int colon = content.indexOf(':');
			if (-1 != colon) {
				String keyString = content.substring(0, colon).trim();
				String typeString = content.substring(colon + 1);
				int lastSpace = keyString.lastIndexOf(' ');
				List<DeclareKey> keys = parseKeys(keyString, lastSpace);
				if (keys.contains(DeclareKey.VAL) || keys.contains(DeclareKey.VAR)) {
					String nameString = keyString.substring(lastSpace + 1).trim();
					return set(compiler, typeString, nameString, keys.contains(DeclareKey.NATIVE));
				}
			}
		}
		return Optional.empty();
	}

	private List<DeclareKey> parseKeys(String keyString, int lastSpace) {
		return Arrays.stream(keyString.substring(0, lastSpace).split(" "))
				.filter(s -> !s.isBlank())
				.map(this::parseKey)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}

	private Optional<Node> set(Compiler compiler, String typeString, String nameString, String value) {
		register.set("assigning", true);
		Type type = compiler.resolveName(typeString.trim());
		stack.enter(nameString, type);
		Node result = compiler.parse(value);
		return Optional.of(new DeclareNode(nameString, type, result));
	}

	private Optional<Node> set(Compiler compiler, String typeString, String nameString, boolean isNative) {
		if (!isNative) {
			register.set("assigning", true);
			Type type = compiler.resolveName(typeString.trim());
			stack.enter(nameString, type);
			return Optional.of(new DeclareNode(nameString, type));
		} else {
			Type type = compiler.resolveName(typeString.trim());
			stack.define(nameString, type);
			return Optional.of(new EmptyNode());
		}
	}

	private Optional<DeclareKey> parseKey(String s) {
		try {
			return Optional.of(DeclareKey.valueOf(s.toUpperCase()));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
}
