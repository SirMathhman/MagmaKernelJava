package com.meti.parse;

import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlockParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("{") && content.endsWith("}")) {
			String value = content.substring(1, content.length() - 1);
			List<String> items = new ArrayList<>();
			StringBuilder builder = new StringBuilder();
			int depth = 0;
			for (char c : value.toCharArray()) {
				if (';' == c && 0 == depth) {
					items.add(builder.toString());
					builder = new StringBuilder();
				} else {
					if ('{' == c) depth++;
					if ('}' == c) depth--;
					builder.append(c);
				}
			}
			items.add(builder.toString());
			List<Node> nodes = items.stream()
					.filter(s -> !s.isBlank())
					.map(compiler::parse)
					.collect(Collectors.toList());
			return Optional.of(new BlockNode(nodes));
		}
		return Optional.empty();
	}
}
