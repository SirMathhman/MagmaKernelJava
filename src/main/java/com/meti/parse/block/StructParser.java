package com.meti.parse.block;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.data.Register;
import com.meti.data.Stack;
import com.meti.parse.Node;
import com.meti.parse.Parser;
import com.meti.parse.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StructParser implements Parser {
	private final Register register;
	private final Stack stack;

	@Inject
	public StructParser(Stack stack, Register register) {
		this.stack = stack;
		this.register = register;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		Optional<Boolean> assigning = register.poll("assigning", Boolean.class);
		if (assigning.isPresent() && assigning.get()) {
			Node node = compiler.parse(content);
			Type type = stack.current().type();
			List<String> values = stack.values();
			stack.exit();
			return Optional.of(new StructNode(type, node, values));
		}
		if (content.startsWith("{") && content.endsWith("}")) {
			String values = content.substring(1, content.length() - 1);
			List<String> list = new ArrayList<>();
			StringBuilder builder = new StringBuilder();
			int depth = 0;
			for (char c : values.toCharArray()) {
				if (';' == c && 0 == depth) {
					list.add(builder.toString());
					builder = new StringBuilder();
				} else {
					if ('{' == c) depth++;
					if ('}' == c) depth--;
					builder.append(c);
				}
			}
			list.add(builder.toString());
			Collection<Node> nodes = list.stream()
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(compiler::parse)
					.collect(Collectors.toList());
			return Optional.of(new BlockNode(nodes));
		} else {
			return Optional.empty();
		}
	}
}
