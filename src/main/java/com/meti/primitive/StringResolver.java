package com.meti.primitive;

import com.meti.Compiler;
import com.meti.parse.Resolver;
import com.meti.parse.Type;
import com.meti.parse.store.PointerType;

import java.util.Optional;

public class StringResolver implements Resolver {
	public static final Type TYPE = new PointerType(PrimitiveType.CHAR);

	@Override
	public Optional<Type> resolveName(String content, com.meti.Compiler compiler) {
		if ("String".equals(content)) {
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
