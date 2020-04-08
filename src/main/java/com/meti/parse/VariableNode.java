package com.meti.parse;

import com.meti.Node;
import com.meti.render.Renderable;
import com.meti.render.VariableRenderable;

import java.util.Collection;
import java.util.Collections;

public class VariableNode implements Node {
	private final String name;

	public VariableNode(String name) {
		this.name = name;
	}

	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(new VariableRenderable(name));
	}
}
