package com.meti.parse;

import com.google.inject.Inject;
import com.meti.Cache;
import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class ImportParser implements Parser {
	private final Cache cache;

	@Inject
	public ImportParser(Cache cache) {
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("import")) {
			String value = content.substring(6).trim();
			cache.getNodes().addFirst(new ImportNode(value));
			return Optional.of(new EmptyNode());
		}
		return Optional.empty();
	}
}
