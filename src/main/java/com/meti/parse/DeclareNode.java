package com.meti.parse;

import com.meti.Instance;
import com.meti.Node;
import com.meti.render.DeclareRenderable;
import com.meti.render.Renderable;

import java.util.Collection;
import java.util.Collections;

public class DeclareNode implements Node {
	private final Instance instance;
	private final String name;

	public DeclareNode(Instance instance, String name) {
		this.instance = instance;
		this.name = name;
	}

	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(new DeclareRenderable(instance.toType(), name));
	}
}
