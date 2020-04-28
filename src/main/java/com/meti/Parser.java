package com.meti;

import java.util.Optional;

public interface Parser {
	Optional<String> parse(String content, Compiler compiler);
}
