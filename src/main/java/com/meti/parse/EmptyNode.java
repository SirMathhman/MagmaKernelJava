package com.meti.parse;

import com.meti.Node;
import com.meti.render.Renderable;

import java.util.Collection;
import java.util.Collections;

public class EmptyNode implements Node {
	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(() -> "");
	}
}
