package com.meti.parse;

import com.meti.Node;
import com.meti.render.Assign;
import com.meti.render.Renderable;

import java.util.Collection;
import java.util.Collections;

public class AssignNode implements Node {
	private final Node from;
	private final Node to;

	public AssignNode(Node to, Node from) {
		this.to = to;
		this.from = from;
	}

	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(new Assign(to.renderSingle(), from.renderSingle()));
	}
}
