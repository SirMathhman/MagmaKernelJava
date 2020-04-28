package com.meti;

import com.meti.parse.Item;

import java.util.Optional;

public interface Parser {
	Optional<Item> parse(String content, Compiler compiler);
}
