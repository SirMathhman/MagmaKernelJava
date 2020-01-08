package com.meti;

import java.util.Optional;

public class CharUnit implements CompoundUnit {
	@Override
	public boolean canCompile(String value) {
		return value.startsWith("'") && value.endsWith("'");
	}

	@Override
	public String compile(String value, Compiler compiler) {
		return value;
	}

	@Override
	public Optional<? extends Type> resolveName(String value, Compiler compiler) {
		return Optional.empty();
	}

	@Override
	public Optional<Type> resolveValue(String value, Compiler compiler) {
		return Optional.of("char")
				.filter(s -> canCompile(value))
				.map(BuildableType::new);
	}
}
