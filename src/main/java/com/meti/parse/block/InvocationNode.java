package com.meti.parse.block;

import com.meti.data.Cache;
import com.meti.parse.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class InvocationNode implements Node {
	protected final Collection<Node> arguments;
	protected final Node caller;

	public InvocationNode(Collection<Node> arguments, Node caller) {
		this.arguments = arguments;
		this.caller = caller;
	}

	@Override
	public String render(Cache cache) {
		String argString = renderArguments(cache);
		String ext = renderExtension();
		return caller.render(cache) + argString + ext;
	}

	private String renderArguments(Cache cache) {
		return arguments.stream()
				.map(node -> node.render(cache))
				.collect(Collectors.joining(",", "(", ")"));
	}

	protected abstract String renderExtension();

	@Override
	public Collection<Node> structures() {
		Collection<Node> structures = new ArrayList<>();
		structures.addAll(caller.structures());
		structures.addAll(arguments.stream()
				.map(Node::structures)
				.flatMap(Collection::stream)
				.collect(Collectors.toList()));
		return structures;
	}
}
