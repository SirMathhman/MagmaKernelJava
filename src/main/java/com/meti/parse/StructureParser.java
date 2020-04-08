package com.meti.parse;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.*;
import com.meti.instance.StructureInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StructureParser implements Parser {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private final Accumulator accumulator;
	private final Cache cache;
	private final Scope scope;
	private int counter = -1;

	@Inject
	public StructureParser(Scope scope, Accumulator accumulator, Cache cache) {
		this.scope = scope;
		this.accumulator = accumulator;
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("{") && content.endsWith("}")) {
			Optional<Instance> type = accumulator.peek();
			if (type.isPresent()) {
				Instance instance = type.get();
				if (instance instanceof StructureInstance) {
					String name = accumulator.name()
							.orElseGet(this::anonymous);
					scope.define(name, instance);
					StructureInstance castedInstance = (StructureInstance) instance;
					Node node = buildBlock(content, compiler);
					cache.getNodes().add(new StructureNode(name, castedInstance.getParameters(),
							castedInstance.getReturnType(), node));
					return Optional.of(new EmptyNode());
				}
			}
			return Optional.of(buildBlock(content, compiler));
		}
		return Optional.empty();
	}

	private String anonymous() {
		counter++;
		char c = ALPHABET.charAt(counter);
		return String.valueOf(c) + counter;
	}

	private Node buildBlock(String content, Compiler compiler) {
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
		return new BlockNode(nodes);
	}
}
