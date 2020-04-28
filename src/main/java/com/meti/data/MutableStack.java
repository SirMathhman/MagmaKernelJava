package com.meti.data;

import com.meti.resolve.Instance;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class MutableStack implements DataStack {
	private DataScope current = new TreeScope("root", null, null);

	@Override
	public Collection<String> asSnapshot() {
		return current.collapse()
				.stream()
				.map(DataScope::getName)
				.collect(Collectors.toList());
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
	public boolean hasParent(Collection<String> names) {
		return false;
	}
}
