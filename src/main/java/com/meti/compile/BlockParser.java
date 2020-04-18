package com.meti.compile;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlockParser implements Parser {
	private final Cache cache;

	@Inject
	public BlockParser(Cache cache) {
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (cache.hasBlock()) {
			Optional<Type> type = cache.pullType();
			if (type.isPresent()) {
				///TODO: return struct node
			}
		}
		if (content.startsWith("{") && content.endsWith("}")) {
			String result = content.substring(1, content.length() - 1);
			List<String> lines = new ArrayList<>();
			StringBuilder builder = new StringBuilder();
			int depth = 0;
			for (char c : result.toCharArray()) {
				if (';' == c && 0 == depth) {
					lines.add(builder.toString());
					builder = new StringBuilder();
				} else {
					if ('{' == c) depth++;
					if ('}' == c) depth--;
					builder.append(c);
				}
			}
			lines.add(builder.toString());
			List<Node> children = lines.stream()
					.filter(s -> !s.isBlank())
					.map(String::trim)
					.map(compiler::parse)
					.collect(Collectors.toList());
			return Optional.of(new BlockNode(children));
		}
		return Optional.empty();
	}

	private static final class BlockNode implements Node {
		private final Collection<Node> children;

		private BlockNode(Collection<Node> children) {
			this.children = children;
		}

		@Override
		public String render() {
			return children.stream()
					.map(Node::render)
					.collect(Collectors.joining("", "{", "}"));
		}
	}
}
