package com.meti.parse.external;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;

import java.util.Optional;

public class ImportParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("import")) {
			String value = content.substring(6);
			return Optional.of(new ImportNode(value));
		} else {
			return Optional.empty();
		}
	}
}
