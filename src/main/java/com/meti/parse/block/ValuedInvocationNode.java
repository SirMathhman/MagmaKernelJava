package com.meti.parse.block;

import com.meti.parse.Node;

import java.util.Collection;

public class ValuedInvocationNode extends InvocationNode {
	ValuedInvocationNode(Node caller, Collection<Node> arguments) {
		super(arguments, caller);
	}

	@Override
	protected String renderExtension() {
		return "";
	}
}
