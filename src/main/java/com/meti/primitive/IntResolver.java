package com.meti.primitive;

import com.meti.Compiler;
import com.meti.parse.Resolver;
import com.meti.parse.Type;

import java.util.Optional;

public class IntResolver implements Resolver {
	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		if (content.equals("Int")) {
			return Optional.of(PrimitiveType.INT);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		try {
			Integer.parseInt(content);
			return Optional.of(PrimitiveType.INT);
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
}
