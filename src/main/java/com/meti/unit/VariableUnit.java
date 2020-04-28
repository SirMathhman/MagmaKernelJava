package com.meti.unit;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.Unit;
import com.meti.data.DataStack;
import com.meti.parse.Item;
import com.meti.parse.Node;
import com.meti.resolve.Instance;

import java.util.Optional;

public class VariableUnit implements Unit {
	private final DataStack stack;

	@Inject
	public VariableUnit(DataStack stack) {
		this.stack = stack;
	}

	@Override
	public Optional<Item> parse(String content, Compiler compiler) {
		return stack.get(content)
				.map(instance -> Node.supplied(content))
				.map(Item::supplied);
	}

	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.empty();
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return stack.get(content);
	}
}
