package com.meti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class Structure implements Node {
	private final Node content;
	private final Type type;
	private final String name;

	public Structure(String name, Type type, Node content) {
		this.name = name;
		this.type = type;
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
		Map<String, Type> children = type.getChildren();
		FunctionUpdateBuilder reduce = appendChildren(builder, children);
		Collection<CacheUpdate> toReturn = new ArrayList<>(content.toUpdates());
		toReturn.add(reduce.build());
		return toReturn;
	}

	private Type buildReturn() {
		return type.getReturnType()
				.orElse(VoidType.INSTANCE);
	}

	private FunctionUpdateBuilder initBuilder(Type returnType) {
		return FunctionUpdateBuilder.create()
				.withName(name)
				.withReturnType(returnType);
	}

	private FunctionUpdateBuilder appendChildren(FunctionUpdateBuilder builder,
	                                             Map<String, ? extends Type> children) {
		return children.keySet()
				.stream()
				.reduce(builder,
						(b, s) -> reduce(children, b, s),
						(prev, current) -> current);
	}

	private FunctionUpdateBuilder reduce(Map<String, ? extends Type> children,
	                                     FunctionUpdateBuilder builder,
	                                     String name) {
		Type type = children.get(name);
		return builder.withParameter(name, type);
	}
}
