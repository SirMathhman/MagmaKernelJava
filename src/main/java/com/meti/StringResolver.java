package com.meti;

import java.util.Optional;

public class StringResolver implements Resolver {
	public static final Type TYPE = new PointerType(PrimitiveType.CHAR);

	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		if (content.equals("String")) {
			return Optional.of(TYPE);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
