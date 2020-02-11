package com.meti.node.transform.operate;

import com.meti.exception.ParseException;
import com.meti.node.Node;
import com.meti.node.Type;
import com.meti.node.primitive.bool.BoolType;
import com.meti.node.primitive.ints.IntType;
import com.meti.node.primitive.strings.StringType;

import java.util.function.Function;

public enum Operations implements Operation {
	ADD("+", "+", false),
	SUBTRACT("-", "-", false),
	MULTIPLY("*", "*", false),
	DIVIDE("/", "/", false),
	MODULUS("%", "%", false),
	EQUALS("==", "==", true),
	NOT_EQUALS("!=", "!=", true),
	LESS_THAN("<", "<", true);

	private final boolean conditional;
	private final String from;
	private final String to;

	Operations(String from, String to, boolean conditional) {
		this.from = from;
		this.to = to;
		this.conditional = conditional;
	}

	@Override
	public boolean isPresent(String content) {
		return content.contains(from);
	}

	@Override
	public String render(Node node0, Node node1) {
		return node0.render() + to + node1.render();
	}

	@Override
	public Node toNode(String content, Function<? super String, ? extends Node> parser) {
		int fromIndex = content.indexOf(from);
		String before = content.substring(0, fromIndex).trim();
		String after = content.substring(fromIndex + from.length()).trim();
		Node beforeNode = parser.apply(before);
		Node afterNode = parser.apply(after);
		return new COperationNode(beforeNode, this, afterNode);
	}

	@Override
	public Type toType(String content, Function<? super String, ? extends Type> applicator) {
		int fromIndex = content.indexOf(from);
		String beforeString = content.substring(0, fromIndex).trim();
		String afterString = content.substring(fromIndex + from.length()).trim();
		Type before = applicator.apply(beforeString);
		Type after = applicator.apply(afterString);
		if (before.equals(after)) {
			return conditional ? BoolType.INSTANCE : before;
		} else {
			if (isAny(before, after, StringType.INSTANCE) && isAny(before, after, IntType.INSTANCE)) {
				return StringType.INSTANCE;
			}
			throw new ParseException("Cannot operate on: " + before + " and " + after);
		}
	}

	private boolean isAny(Type t0, Type t1, Type expected) {
		return t0.equals(expected) || t1.equals(expected);
	}
}
