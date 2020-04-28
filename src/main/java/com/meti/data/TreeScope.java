package com.meti.data;

import com.meti.resolve.Instance;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TreeScope implements DataScope {
	private final Map<String, DataScope> children = new HashMap<>();
	private final Instance instance;
	private final String name;
	private final DataScope parent;

	public TreeScope(String name, Instance instance, DataScope parent) {
		this.name = name;
		this.instance = instance;
		this.parent = parent;
	}

	@Override
	public Optional<DataScope> get(String name) {
		return Optional.ofNullable(children.get(name))
				.or(() -> getFromParent(name));
	}

	@Override
	public Instance getInstance() {
		return instance;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Optional<DataScope> getParent() {
		return Optional.ofNullable(parent);
	}

	@Override
	public DataScope set(String name, Instance instance) {
		DataScope scope = new TreeScope(name, instance, this);
		children.put(name, scope);
		return scope;
	}

	private Optional<DataScope> getFromParent(String name) {
		return getParent().flatMap(p -> parent.get(name));
	}
}
