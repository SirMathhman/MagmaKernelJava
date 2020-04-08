package com.meti.instance;

import com.meti.Compiler;
import com.meti.Instance;
import com.meti.Resolver;

import java.util.Optional;

public class DoubleResolver implements Resolver {
	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.of(DoubleInstance.INSTANCE)
				.filter(i -> "Double".equals(content));
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
