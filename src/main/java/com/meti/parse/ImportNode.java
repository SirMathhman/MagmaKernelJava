package com.meti.parse;

import com.meti.Node;
import com.meti.render.Renderable;

import java.util.Collection;
import java.util.Collections;

public class ImportNode implements Node {
	private final String value;

	public ImportNode(String value) {
		this.value = value;
	}

	@Override
	public Collection<? extends Renderable> render() {
		return Collections.singleton(() -> "#include <" + value + ".h>\n");
	}
}
