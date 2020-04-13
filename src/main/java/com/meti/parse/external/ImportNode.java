package com.meti.parse.external;

import com.meti.data.Cache;
import com.meti.parse.Node;

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
		cache.append(-1, "#include <" + value.trim() + ".h>\n");
		return "";
	}
}
