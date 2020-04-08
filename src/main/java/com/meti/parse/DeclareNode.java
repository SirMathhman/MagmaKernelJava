package com.meti.parse;

import com.meti.Instance;
import com.meti.Node;
import com.meti.render.DeclareRenderable;
import com.meti.render.Renderable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DeclareNode implements Node {
	private final Node init;
	private final Instance instance;
	private final String name;

	public DeclareNode(Instance instance, String name, Node init) {
		this.instance = instance;
		this.name = name;
		this.init = init;
	}

	@Override
	public Collection<? extends Renderable> render() {
		if (null == init) {
			return Collections.singleton(new DeclareRenderable(instance.toType(), name, null));
		} else {
			return List.of(new DeclareRenderable(instance.toType(), name, init.renderSingle()));
		}
	}
}
