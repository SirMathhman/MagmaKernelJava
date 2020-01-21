package com.meti.node.value.primitive.character;

import com.meti.compile.Compiler;
import com.meti.node.Node;
import com.meti.exception.ParseException;
import com.meti.node.Parser;

import java.util.Optional;

public class CharParser implements Parser {
	@Override
	public Optional<Node> parse(String value, Compiler compiler) {
		String trim = value.trim();
		if (trim.startsWith("'") && trim.endsWith("'")) {
			String charString = trim.substring(1, trim.length() - 1);
			if (3 == charString.length()) {
				return Optional.of(new CharNode(charString.charAt(1)));
			} else {
				throw new ParseException("Too many characters.");
			}
		}
		return Optional.empty();
	}

}