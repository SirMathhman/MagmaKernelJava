package com.meti;

import java.util.Optional;

public class FloatResolver implements Resolver {
	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		if (content.equals("Float")) {
			return Optional.of(PrimitiveType.FLOAT);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
