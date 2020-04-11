package com.meti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class Structure implements Item {
	private final Item content;
	private final Instance instance;
	private final String name;

	public Structure(String name, Instance instance, Item content) {
		this.name = name;
		this.instance = instance;
		this.content = content;
	}

	@Override
	public boolean hasMultiple() {
		return false;
	}

	@Override
	public boolean hasStructure() {
		return true;
	}

	@Override
	public Collection<CacheUpdate> toUpdates() {
		if (content.hasStructure()) {
			Collection<CacheUpdate> collection = content.toUpdates();
			return Collections.emptyList();
		} else {
			return renderWithoutSubStructures();
		}
	}

	private Collection<CacheUpdate> renderWithoutSubStructures() {
		Type returnType = buildReturn();
		FunctionUpdateBuilder builder = initBuilder(returnType);
		Map<String, Instance> children = instance.children();
		FunctionUpdateBuilder reduce = appendChildren(builder, children);
		Collection<CacheUpdate> toReturn = new ArrayList<>(content.toUpdates());
		toReturn.add(reduce.build());
		return toReturn;
	}

	private Type buildReturn() {
		return instance.asReturn()
				.map(Instance::toType)
				.orElse(VoidType.INSTANCE);
	}

	private FunctionUpdateBuilder initBuilder(Type returnType) {
		return FunctionUpdateBuilder.create()
				.withName(name)
				.withReturnType(returnType);
	}

	private FunctionUpdateBuilder appendChildren(FunctionUpdateBuilder builder,
	                                             Map<String, ? extends Instance> children) {
		return children.keySet()
				.stream()
				.reduce(builder,
						(b, s) -> reduce(children, b, s),
						(prev, current) -> current);
	}

	private FunctionUpdateBuilder reduce(Map<String, ? extends Instance> children,
	                                     FunctionUpdateBuilder builder,
	                                     String name) {
		Instance instance = children.get(name);
		Type type = instance.toType();
		return builder.withParameter(name, type);
	}
}
