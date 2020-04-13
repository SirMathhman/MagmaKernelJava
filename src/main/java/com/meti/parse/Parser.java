package com.meti.parse;

import com.meti.Compiler;

import java.util.Optional;

public interface Parser {
	Optional<Node> parse(String content, Compiler compiler);
}
