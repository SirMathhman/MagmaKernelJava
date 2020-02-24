package com.meti.node.struct.invoke;

import com.meti.node.Node;
import com.meti.node.Statement;
import com.meti.node.Type;
import com.meti.node.primitive.special.VoidType;

import java.util.List;

public class Invocation implements Statement {
	private final List<? extends Node> arguments;
	private final Node callerNode;
	private final Type returnType;

	public Invocation(Type returnType, Node callerNode, List<? extends Node> arguments) {
		this.arguments = arguments;
		this.callerNode = callerNode;
		this.returnType = returnType;
	}

	@Override
	public Node build() {
		return returnType.equals(VoidType.INSTANCE) ?
				new CVoidInvocationNode(callerNode, arguments) :
				new CInvocationNode(callerNode, arguments);
	}
}
