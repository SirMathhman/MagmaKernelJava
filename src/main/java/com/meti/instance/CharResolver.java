package com.meti.instance;

import com.meti.Compiler;
import com.meti.Instance;
import com.meti.Resolver;

import java.util.Optional;

public class CharResolver implements Resolver {
	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.of(CharInstance.INSTANCE)
				.filter(i -> "Char".equals(content));
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return Optional.of(CharInstance.INSTANCE)
				.filter(i -> content.startsWith("'") && content.endsWith("'"));
	}
}
