package com.meti.data;

import com.meti.resolve.Instance;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TreeStack implements DataStack {
	private final DataScope root = new TreeScope("root", null, null);
	private DataScope current = root;

	@Override
	public List<String> asSnapshot() {
		if (current == root) {
			return Collections.emptyList();
		} else {
			return current.collapse()
					.stream()
					.map(DataScope::getName)
					.collect(Collectors.toList());
		}
	}

	@Override
	public void define(String name, Instance instance) {
		current.set(name, instance);
	}

	@Override
	public void enter(String name, Instance instance) {
		current = current.set(name, instance);
	}

	@Override
	public void exit() {
		Optional<DataScope> parent = current.getParent();
		parent.ifPresent(dataScope -> current = dataScope);
	}

	@Override
	public Optional<Instance> get(String name) {
		return current.get(name).map(DataScope::getInstance);
	}

	@Override
	public Optional<Instance> get(List<String> names) {
		return root.get(names).map(DataScope::getInstance);
	}

	@Override
	public String getName() {
		return current.getName();
	}

	@Override
	public boolean hasParent(List<String> names) {
		return root.get(names)
				.map(DataScope::isParent)
				.orElseThrow(() -> absoluteNotFound(names));
	}

	private IllegalArgumentException absoluteNotFound(List<String> names) {
		String joinedNames = String.join(",", names);
		return new IllegalArgumentException(joinedNames + " is not defined.");
	}
}
