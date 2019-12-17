package com.meti.unit;

import com.meti.Compiler;

import java.util.Optional;

public class ElseUnit implements Unit {
	@Override
	public Optional<String> parse(String input, Compiler compiler) {
		if (input.startsWith("else")) {
			String content = compiler.compile(input.substring(4));
			return Optional.of("else" + content);
		}
		return Optional.empty();
	}
}
