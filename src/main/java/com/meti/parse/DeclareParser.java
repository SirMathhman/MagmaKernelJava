package com.meti.parse;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.Parser;
import com.meti.data.DataStack;
import com.meti.resolve.Instance;
import com.meti.resolve.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeclareParser implements Parser {
	private final DataStack stack;

	@Inject
	public DeclareParser(DataStack stack) {
		this.stack = stack;
	}

	//val x : Int
	@Override
	public Optional<Item> parse(String content, Compiler compiler) {
		int colon = content.indexOf(':');
		if (-1 != colon) {
			String before = content.substring(0, colon).trim();
			String typeString = content.substring(colon + 1).trim();
			int lastSpace = before.lastIndexOf(' ');
			String keyString = before.substring(0, lastSpace).trim();
			String name = before.substring(lastSpace + 1).trim();
			List<DeclareKey> collect = parseKeysInList(keyString);
			if (collect.contains(DeclareKey.VAL) || collect.contains(DeclareKey.VAR)) {
				List<String> snapshot = stack.asSnapshot();
				Instance type = compiler.resolveName(typeString);
				stack.define(name, type);
				return Optional.of(new DeclareItem(stack, snapshot, name, type));
			}
			return Optional.empty();
		}
		return Optional.empty();
	}

	private List<DeclareKey> parseKeysInList(String keyString) {
		return Arrays.stream(keyString.split(" "))
				.filter(s -> !s.isBlank())
				.map(String::trim)
				.map(String::toUpperCase)
				.map(this::parseKey)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}

	private Optional<DeclareKey> parseKey(String name1) {
		try {
			return Optional.of(DeclareKey.valueOf(name1));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	private enum DeclareKey {
		NATIVE,
		VAL,
		VAR
	}

	private static final class DeclareItem implements Item {
		private final Instance instance;
		private final String name;
		private final List<String> snapshot;
		private final DataStack stack;

		private DeclareItem(DataStack stack, List<String> parentSnapshot, String name, Instance
				instance) {
			this.stack = stack;
			this.snapshot = parentSnapshot;
			this.name = name;
			this.instance = instance;
		}

		@Override
		public Node build() {
			boolean isParent = stack.hasParent(snapshot);
			if (isParent) {
				return Node.EMPTY;
			} else {
				return new DeclareNode(name, instance.build());
			}
		}
	}

	private static final class DeclareNode implements Node {
		private final String name;
		private final Type type;

		private DeclareNode(String name, Type type) {
			this.name = name;
			this.type = type;
		}

		@Override
		public String render() {
			return type.render(name);
		}
	}
}
