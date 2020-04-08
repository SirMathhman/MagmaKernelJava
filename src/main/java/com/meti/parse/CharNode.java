package com.meti.parse;

import com.meti.Node;
import com.meti.render.Renderable;
import com.meti.render.primitive.CharRenderable;

import java.util.Collection;
import java.util.Collections;

public class CharNode implements Node {
	private final char value;

	public CharNode(char value) {
		this.value = value;
	}

	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(new CharRenderable(value));
	}
}
