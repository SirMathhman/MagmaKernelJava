package com.meti.render;

public class Include implements Renderable {
	private final String header;

	public Include(String header) {
		this.header = header;
	}

	@Override
	public String render() {
		return "#include <" + header + ".h>\n";
	}
}
