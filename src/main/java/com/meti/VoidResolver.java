package com.meti;

import java.util.Optional;

public class VoidResolver implements Resolver {
	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		if (content.equals("Void")) {
			return Optional.of(PrimitiveType.VOID);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
