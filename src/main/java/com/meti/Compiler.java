package com.meti;

public interface Compiler {
	Node parse(String content);

	Type resolveName(String content);

	Type resolveValue(String content);
}
