package com.meti;

public interface Compiler {
	String parse(String content);

	Instance resolveName(String content);

	Instance resolveValue(String content);
}
