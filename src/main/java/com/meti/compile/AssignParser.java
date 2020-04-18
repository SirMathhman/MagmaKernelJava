package com.meti.compile;

import java.util.Optional;

public class AssignParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int equals = content.indexOf('=');
		if (equals != -1) {
			String toString = content.substring(0, equals).trim();
			String fromString = content.substring(equals + 1).trim();
			Node toNode = compiler.parse(toString);
			Node fromNode = compiler.parse(fromString);
			return Optional.of(new AssignNode(toNode, fromNode));
		}
		return Optional.empty();
	}

	private static class AssignNode implements Node {
		private final Node fromNode;
		private final Node toNode;

		public AssignNode(Node toNode, Node fromNode) {
			this.toNode = toNode;
			this.fromNode = fromNode;
		}

		@Override
		public String render() {
			return toNode.render() + "=" + fromNode.render() + ";";
		}
	}
}
