package com.meti;

import com.meti.parse.Item;
import com.meti.resolve.Instance;

public interface Compiler {
	Item parse(String content);

	Instance resolveName(String content);

	Instance resolveValue(String content);
}
