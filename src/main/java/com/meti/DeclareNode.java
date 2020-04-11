package com.meti;

import java.util.Collection;
import java.util.Collections;

public class DeclareNode implements Node {
	private final RenderedNode init;
	private final String name;
	private final Type type;

	public DeclareNode(String name, Type type) {
		this(name, type, null);
	}

	public DeclareNode(String name, Type type, RenderedNode init) {
		this.name = name;
		this.type = type;
		this.init = init;
	}

	@Override
	public boolean hasMultiple() {
		return false;
	}

	@Override
	public boolean hasStructure() {
		return null != init && init.hasStructure();
	}

	@Override
	public Collection<CacheUpdate> toUpdates() {
		StringBuilder builder = new StringBuilder(type.render(name));
		builder = appendInit(builder).append(";");
		return Collections.singleton(new LineUpdate(builder.toString()));
	}

	private StringBuilder appendInit(StringBuilder builder) {
		return null != init ? builder.append("=").append(init.render()) : builder;
	}
}
