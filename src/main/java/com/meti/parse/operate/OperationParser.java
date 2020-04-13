package com.meti.parse.operate;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;

import java.util.Map;
import java.util.Optional;

public class OperationParser implements Parser {
	private static final Map<String, String> OPERATIONS = Map.of(
			"+", "+"
	);

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		return OPERATIONS.keySet()
				.stream()
				.map(s -> buildOperation(content, compiler, s))
				.flatMap(Optional::stream)
				.findFirst();
	}

	private Optional<Node> buildOperation(String content, Compiler compiler, String s) {
		int index = content.indexOf(s);
		if (-1 == index) return Optional.empty();
		String before = content.substring(0, index);
		String after = content.substring(index + 1);
		Node value0 = compiler.parse(before.trim());
		Node value1 = compiler.parse(after.trim());
		return Optional.of(new OperationNode(value0, value1, OPERATIONS.get(s)));
	}
}
