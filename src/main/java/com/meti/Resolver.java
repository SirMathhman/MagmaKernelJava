package com.meti;

import com.meti.resolve.Instance;

import java.util.Optional;

public interface Resolver {
	Optional<Instance> resolveName(String content, Compiler compiler);

	Optional<Instance> resolveValue(String content, Compiler compiler);
}
