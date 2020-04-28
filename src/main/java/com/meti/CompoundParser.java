package com.meti;

import java.util.Collection;
import java.util.Optional;

public class CompoundParser implements Parser {
	private final Collection<Parser> children;

	public CompoundParser(Collection<Parser> children) {
		this.children = children;
	}

	@Override
	public Optional<String> parse(String content, Compiler compiler) {
		return children.stream()
				.map(child -> child.parse(content, compiler))
				.flatMap(Optional::stream)
				.findFirst();
	}
}
