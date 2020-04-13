package com.meti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class InvocationNode implements Node {
	private final Collection<Node> arguments;
	private final Node caller;
	private final boolean returnsVoid;

	public InvocationNode(Node caller, Collection<Node> arguments, boolean returnsVoid) {
		this.caller = caller;
		this.arguments = arguments;
		this.returnsVoid = returnsVoid;
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
		String ext = (returnsVoid) ? ";" : "";
		return caller.render(cache) + argString + ext;
	}

	private String renderArguments(Cache cache) {
		return arguments.stream()
				.map(node -> node.render(cache))
				.collect(Collectors.joining(",", "(", ")"));
	}
}
