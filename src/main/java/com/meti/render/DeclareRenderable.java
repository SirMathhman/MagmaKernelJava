package com.meti.render;

import com.meti.type.Type;

public class DeclareRenderable implements Renderable {
	private final Renderable init;
	private final String name;
	private final Type type;

	public DeclareRenderable(Type type, String name) {
		this(type, name, null);
	}

	public DeclareRenderable(Type type, String name, Renderable init) {
		this.type = type;
		this.name = name;
		this.init = init;
	}

	@Override
	public String render() {
		if (null == init) {
			return type.render(name) + ";";
		} else {
			String init = this.init.render();
			return init.isBlank() ? "" : type.render(name) + "=" + init + ";";
		}
	}
}
