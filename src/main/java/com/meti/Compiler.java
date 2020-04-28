package com.meti;

import com.meti.resolve.Type;

public interface Compiler {
	String parse(String content);

	Type resolveName(String content);

	Type resolveValue(String content);
}
