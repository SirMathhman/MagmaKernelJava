package com.meti;

import com.meti.type.Type;

public interface Compiler {
	String parse(String content);

	Type resolveName(String content);

	Type resolveType(String content);
}
