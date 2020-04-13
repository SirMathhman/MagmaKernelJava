package com.meti.parse.operate;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;
import com.meti.parse.Type;

import java.util.Optional;

public class CastParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		if (content.startsWith("<")) {
			char[] charArray = content.toCharArray();
			int index = -1;
			int depth = 0;
			for (int i = 1; i < charArray.length; i++) {
				char c = charArray[i];
				if (c == '>' && depth == 0) {
					index = i;
					break;
				} else {
					if (c == '<') depth++;
					if (c == '>') depth--;
				}
			}
			String typeString = content.substring(1, index);
			String valueString = content.substring(index + 1);
			Type type = compiler.resolveName(typeString);
			Node value = compiler.parse(valueString);
			return Optional.of(new CastNode(type, value));
		}
		return Optional.empty();
	}
}
