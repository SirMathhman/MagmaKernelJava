package com.meti.parse;

import com.meti.Compiler;

import java.util.Optional;

public interface Resolver {
	Optional<Type> resolveName(String content, Compiler compiler);

	Optional<Type> resolveValue(String content, Compiler compiler);
}
