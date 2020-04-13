package com.meti;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StructNode implements Node {
	private final Node content;
	private final String name;
	private final Type type;

	public StructNode(String name, Type type, Node content) {
		this.name = name;
		this.type = type;
		this.content = content;
	}

	@Override
	public Collection<Node> structures() {
		return Collections.singleton(this);
	}

	@Override
	public String render(Cache cache) {
		StructType cast = type();
		Collection<Node> structures = content.structures();
		if (structures.isEmpty()) {
			String header = cast.renderHeader(implName());
			String footer = content.render(cache);
			cache.append(1, header + footer);
		} else {
			Type type = new NativeStructType(name);
			structures.stream()
					.map(StructNode.class::cast)
					.map(StructNode::type)
					.forEach(structType -> structType.appendParameter(name, type));
			cache.append(0, cast.renderStruct(name));
			String header = cast.renderHeader(implName());
			ParentNode contentToRender = content instanceof ParentNode ? (ParentNode) content : new BlockNode(content);
			List<Node> children = contentToRender.children();
			Node construction = cast.renderConstruction();
			children.add(0, new DeclareNode(name, type, construction));
			String footer = contentToRender.render(cache);
			cache.append(1, header + footer);
		}
		return implName();
	}

	private StructType type() {
		if (!(type instanceof StructType)) throw new IllegalArgumentException(type + " is not a structure.");
		return (StructType) this.type;
	}

	private String implName() {
		return name + "_";
	}
}
