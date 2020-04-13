package com.meti;

import java.util.Optional;

public class PointerResolver implements Resolver {
	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		if (content.startsWith("Pointer")) {
			String after = content.substring(7);
			if (after.startsWith("<") && after.endsWith(">")) {
				String typeString = after.substring(1, after.length() - 1);
				Type type = compiler.resolveName(typeString);
				return Optional.of(new PointerType(type));
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
