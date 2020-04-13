package com.meti.primitive;

import com.meti.Compiler;
import com.meti.parse.Resolver;
import com.meti.parse.Type;

import java.util.Optional;

public class VoidResolver implements Resolver {
	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		if ("Void".equals(content)) {
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
