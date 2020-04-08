package com.meti.parse;

import com.meti.Node;
import com.meti.render.Renderable;

import java.util.Collection;
import java.util.Collections;

public class ReturnNode implements Node {
	private final Node value;

	public ReturnNode(Node value) {
		this.value = value;
	}

	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(new ReturnRenderable(value.renderSingle()));
	}
}
