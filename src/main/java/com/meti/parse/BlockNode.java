package com.meti.parse;

import com.meti.Node;
import com.meti.render.Renderable;
import com.meti.render.block.BlockRenderable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlockNode implements Node {
	private final Collection<? extends Node> nodes;

	public BlockNode(Collection<? extends Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public Collection<? extends Renderable> render() {
		List<? extends Renderable> renderables = renderNodes();
		return Collections.singleton(new BlockRenderable(renderables));
	}

	private List<? extends Renderable> renderNodes() {
		return nodes.stream()
				.map(Node::render)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}
}
