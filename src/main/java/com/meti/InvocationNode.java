package com.meti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class InvocationNode implements Node {
	private final Collection<Node> arguments;
	private final Node caller;

	public InvocationNode(Node caller, Collection<Node> arguments) {
		this.caller = caller;
		this.arguments = arguments;
	}

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

	@Override
	public String render(Cache cache) {
		String argString = renderArguments(cache);
		return caller.render(cache) + argString;
	}

	private String renderArguments(Cache cache) {
		return arguments.stream()
				.map(node -> node.render(cache))
				.collect(Collectors.joining(",", "(", ")"));
	}
}
