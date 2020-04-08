package com.meti.parse;

import com.google.inject.Inject;
import com.meti.Cache;
import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class AssignParser implements Parser {
	private final Cache cache;

	@Inject
	public AssignParser(Cache cache) {
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int equals = content.indexOf('=');
		if (-1 == equals || "=>".equals(content.substring(equals, equals + 2))) return Optional.empty();
		String to = content.substring(0, equals).trim();
		String from = content.substring(equals + 1).trim();
		cache.setInstance(compiler.resolveValue(to));
		Node toNode = compiler.parse(to);
		Node fromNode = compiler.parse(from);
		return Optional.of(new AssignNode(toNode, fromNode));
	}
}
