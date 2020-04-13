package com.meti;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StructNode implements Node {
	private final Node content;
	private final List<String> names;
	private final Type type;

	public StructNode(Type type, Node content, String... names) {
		this(type, content, List.of(names));
	}

	public StructNode(Type type, Node content, List<String> names) {
		this.names = names;
		this.type = type;
		this.content = content;
	}

	public StructNode(Type type, Node content, String name) {
		this(type, content, Collections.singletonList(name));
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
			StringBuilder builder = new StringBuilder();
			if (names.size() > 1) {
				String parent = names.get(names.size() - 2);
				String actual = names.get(names.size() - 1);
				builder.append(";")
						.append(parent)
						.append(".")
						.append(actual)
						.append("=")
						.append(actual);
			}
			return implName() + builder;
		} else {
			String name = lastName();
			Type type = new NativeStructType(name);
			structures.stream()
					.map(StructNode.class::cast)
					.forEach(structNode -> {
						structNode.type().appendParameter(name, new PointerType(PrimitiveType.VOID));
						cast.appendChild(structNode.lastName(), structNode.type);
					});
			cache.append(0, cast.renderStruct(name));
			String header = cast.renderHeader(implName());
			ParentNode contentToRender = content instanceof ParentNode ? (ParentNode) content : new BlockNode(content);
			List<Node> children = contentToRender.children();
			Node construction = cast.renderConstruction();
			children.add(0, new DeclareNode(name, type, construction));
			String footer = contentToRender.render(cache);
			cache.append(1, header + footer);
			return implName();
		}
	}

	private StructType type() {
		if (type instanceof StructType) return (StructType) type;
		throw new IllegalArgumentException(type + " is not a structure.");
	}

	private String implName() {
		return String.join("_", names) + "_";
	}

	private String lastName() {
		if (names.isEmpty()) throw new IllegalStateException("No names provided.");
		return names.get(names.size() - 1);
	}
}
