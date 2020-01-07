package com.meti;

import java.util.Optional;

public class ReturnUnit implements Unit {
	@Override
	public boolean canCompile(String value) {
		return value.startsWith("return");
	}

	@Override
	public String compile(String value, Compiler compiler) {
		String substring = value.substring(6);
		return "return " + compiler.compileOnly(substring) + ";";
	}

	@Override
	public Optional<String> resolveName(String value, Compiler compiler) {
		return Optional.empty();
	}

	@Override
	public Optional<String> resolveValue(String value, Compiler compiler) {
		return Optional.empty();
	}
}
