package com.meti.parse;

import com.meti.Compiler;
import com.meti.Node;
import com.meti.Parser;

import java.util.Optional;

public class CharParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("'") && content.endsWith("'")) {
			String substring = content.substring(1, content.length() - 1);
			char[] array = substring.toCharArray();
			if (1 == array.length) {
				return Optional.of(new CharNode(array[0]));
			} else {
				throw new IllegalArgumentException("Not a character: " + substring);
			}
		} else {
			return Optional.empty();
		}
	}
}
