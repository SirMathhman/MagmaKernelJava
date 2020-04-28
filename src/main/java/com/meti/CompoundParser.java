package com.meti;

import com.meti.parse.Item;

import java.util.Collection;
import java.util.Optional;

public class CompoundParser implements Parser {
	private final Collection<? extends Parser> children;

	public CompoundParser(Collection<? extends Parser> children) {
		this.children = children;
	}

	@Override
	public Optional<Item> parse(String content, Compiler compiler) {
		return children.stream()
				.map(child -> child.parse(content, compiler))
				.flatMap(Optional::stream)
				.findFirst();
	}
}
