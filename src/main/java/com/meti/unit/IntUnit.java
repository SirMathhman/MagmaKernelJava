package com.meti.unit;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.Resolver;
import com.meti.parse.Item;
import com.meti.parse.Node;
import com.meti.resolve.Instance;
import com.meti.resolve.Type;

import java.util.Optional;

public class IntUnit implements Parser, Resolver {
	private static final Type TYPE = Type.supplied("int");

	@Override
	public Optional<Item> parse(String content, Compiler compiler) {
		return parse(content)
				.map(String::valueOf)
				.map(Node::supplied)
				.map(Item::supplied);
	}

	private Optional<Integer> parse(String content) {
		try {
			return Optional.of(Integer.parseInt(content));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.of(content)
				.filter("Int"::equals)
				.map(value -> TYPE)
				.map(Instance::supplied);
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return parse(content)
				.map(integer -> TYPE)
				.map(Instance::supplied);
	}
}
