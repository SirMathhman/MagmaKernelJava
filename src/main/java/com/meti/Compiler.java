package com.meti;

public interface Compiler {
	String parse(String content);

	Type resolveName(String content);

	Type resolveType(String content);
}
