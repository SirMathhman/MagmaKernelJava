package com.meti.unit;

import com.meti.Compiler;
import com.meti.CompoundUnit;
import com.meti.Unit;
import com.meti.parse.Item;
import com.meti.parse.Node;
import com.meti.resolve.Instance;
import com.meti.resolve.Type;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public enum PrimitiveUnit implements Unit {
	INT(Type.supplied("int"), "Int", s -> {
		return parseNumber(s, Integer::parseInt);
	}),
	SHORT(Type.supplied("short"), "Short", s -> {
		return parseNumber(s, Short::parseShort);
	}),
	LONG(Type.supplied("long"), "Long", s -> {
		return parseNumber(s, Long::parseLong);
	}),
	FLOAT(Type.supplied("float"), "Float", s -> {
		return parseNumber(s, Float::parseFloat);
	}),
	DOUBLE(Type.supplied("double"), "Double", s -> {
		return parseNumber(s, Double::parseDouble);
	}),
	CHAR(Type.supplied("int"), "Int", s -> {
		return parseNumber(s, PrimitiveUnit::parseCharacter);
	});

	public static final Unit INSTANCE = new CompoundUnit(List.of(values()));

	private final Function<String, Optional<?>> builder;
	private final String name;
	private final Type type;

	PrimitiveUnit(Type type, String name, Function<String, Optional<?>> builder) {
		this.type = type;
		this.builder = builder;
		this.name = name;
	}

	private static Character parseCharacter(String s) {
		if (s.startsWith("'") && s.endsWith("'")) {
			String value = s.substring(1, s.length() - 1);
			char[] array = value.toCharArray();
			return array[0];
		} else {
			throw new IllegalArgumentException("Not a character: " + s);
		}
	}

	private static <T> Optional<T> parseNumber(String content, Function<String, T> function) {
		try {
			return Optional.of(function.apply(content));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Item> parse(String content, Compiler compiler) {
		return builder.apply(content)
				.map(String::valueOf)
				.map(Node::supplied)
				.map(Item::supplied);
	}

	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.of(content)
				.filter(name::equals)
				.map(value -> type)
				.map(Instance::supplied);
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return builder.apply(content)
				.map(integer -> type)
				.map(Instance::supplied);
	}
}
