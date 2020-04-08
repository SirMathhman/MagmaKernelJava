package com.meti.instance;

import com.meti.Compiler;
import com.meti.Instance;
import com.meti.Resolver;

import java.util.Optional;

public class IntResolver implements Resolver {
	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.of(IntInstance.INSTANCE)
				.filter(i -> "Int".equals(content));
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return Optional.of(IntInstance.INSTANCE)
				.filter(i -> content.endsWith("i"))
				.or(() -> testInt(content));
	}

	private Optional<? extends Instance> testInt(String content) {
		try {
			Integer.parseInt(content);
			return Optional.of(IntInstance.INSTANCE);
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
