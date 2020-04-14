package com.meti.parse.store;

import com.meti.parse.Node;
import com.meti.parse.Type;
import com.meti.parse.util.NullNode;
import com.meti.primitive.PrimitiveType;

public class PointerType implements Type {
	public static final Type ANY = new PointerType(PrimitiveType.VOID);
	private final Type parent;

	public PointerType(Type parent) {
		this.parent = parent;
	}

	@Override
	public Node defaultValue() {
		return NullNode.INSTANCE;
	}

	@Override
	public String render(String name) {
		return parent.render("*" + name);
	}
}
