package com.meti;

public interface Compiler {
	Node parse(String content);

	Instance resolveName(String content);

	Instance resolveValue(String content);
}
