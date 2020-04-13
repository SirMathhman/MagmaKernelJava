package com.meti;

import com.meti.parse.Node;
import com.meti.parse.Type;

public interface Compiler {
	Node parse(String content);

	Type resolveName(String content);

	Type resolveValue(String content);
}
