package com.meti.parse;

import com.google.inject.Inject;
import com.meti.Cache;
import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class StructureParser implements Parser {
	private final Cache cache;

	@Inject
	public StructureParser(Cache cache) {
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		//main = {}
		//{}
		return Optional.empty();
	}
}
