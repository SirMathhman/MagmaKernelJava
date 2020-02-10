package com.meti.node.struct;

import com.meti.Compiler;
import com.meti.Resolver;
import com.meti.node.Type;
import com.meti.parse.Declarations;

import java.util.Optional;

public class ThisResolver implements Resolver {
	private final Declarations declarations;

	public ThisResolver(Declarations declarations) {
		this.declarations = declarations;
	}

	@Override
	public Optional<Type> resolveName(String content, Compiler compiler) {
		return Optional.empty();
	}

	@Override
	public Optional<Type> resolveValue(String content, Compiler compiler) {
		return Optional.of(content)
				.map(String::trim)
				.filter("this"::equals)
				.map(s -> buildType());
	}

	private Type buildType() {
		return declarations.current().toDefinedStruct();
	}
}
