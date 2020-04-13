package com.meti.parse.block;

import com.meti.Compiler;
import com.meti.parse.Node;
import com.meti.parse.Parser;
import com.meti.parse.Type;

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
					.map(String::trim)
					.map(compiler::parse)
					.collect(Collectors.toList());
			Type callerType = compiler.resolveValue(callerString);
			if (callerType instanceof StructType) {
				boolean returningVoid = callerType.isReturningVoid();
				Node node = (returningVoid) ? new VoidInvocationNode(caller, arguments) :
						new ValuedInvocationNode(caller, arguments);
				return Optional.of(node);
			} else {
				throw new IllegalStateException(callerType + " is not a structure.");
			}
		}
	}
}
