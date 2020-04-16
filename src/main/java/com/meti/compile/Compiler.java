package com.meti.compile;

public interface Compiler {
	Node parse(String content);

	Type resolveName(String content);

	Type resolveValue(String content);
}
