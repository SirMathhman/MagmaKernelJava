package com.meti;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InvocationParser implements Parser {
	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int index = -1;
		int depth = 0;
		char[] charArray = content.toCharArray();
		for (int i = charArray.length - 2; 0 <= i; i--) {
			char c = charArray[i];
			if ('(' == c && depth == 0) {
				index = i;
				break;
			} else {
				if (')' == c) depth++;
				if ('(' == c) depth--;
			}
		}
		if (-1 == index) {
			return Optional.empty();
		} else {
			String callerString = content.substring(0, index);
			String argString = content.substring(index + 1, content.length() - 1);
			Node caller = compiler.parse(callerString);
			List<Node> arguments = Arrays.stream(argString
					.split(","))
					.filter(s -> !s.isBlank())
					.map(compiler::parse)
					.collect(Collectors.toList());
			return Optional.of(new InvocationNode(caller, arguments));
		}
	}
}
