package com.meti;

import java.util.Collection;
import java.util.Collections;

class ImportNode implements Node {
	private final String value;

	public ImportNode(String value) {
		this.value = value;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.emptySet();
	}

	@Override
	public String render(Cache cache) {
		cache.append(-1, "#include <" + value + ".h>\n");
		return "";
	}
}
