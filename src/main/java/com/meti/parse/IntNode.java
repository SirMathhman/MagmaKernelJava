package com.meti.parse;

import com.meti.Node;
import com.meti.render.Renderable;
import com.meti.render.primitive.IntRenderable;

import java.util.Collection;
import java.util.Collections;

public class IntNode implements Node {
	private final int value;

	public IntNode(int value) {
		this.value = value;
	}

	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(new IntRenderable(value));
	}
}
