package com.meti.parse.block;

import com.meti.data.Cache;
import com.meti.parse.Node;
import com.meti.parse.Type;
import com.meti.parse.store.DeclareNode;
import com.meti.parse.store.PointerType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimpleStructNode implements StructNode {
	private static final int CONTENT = 1;
	private static final int STRUCT = 0;
	private final Node content;
	private final List<String> names;
	private final StructType type;

	public SimpleStructNode(Type type, Node content, String... names) {
		this(type, content, List.of(names));
	}

	SimpleStructNode(Type type, Node content, List<String> names) {
		this.names = names;
		this.content = content;
		this.type = check(type);
	}

	private StructType check(Type type) {
		if (type instanceof StructType) return (StructType) type;
		throw new IllegalArgumentException(type + " is not a structure.");
	}

	public SimpleStructNode(Type type, Node content, String name) {
		this(type, content, Collections.singletonList(name));
	}

	@Override
	public String render(Cache cache) {
		Collection<Node> children = content.structures();
		return children.isEmpty() ?
				renderWithoutChildren(cache, type) :
				renderWithChildren(cache, type, children);
	}

	@Override
	public Collection<Node> structures() {
		return Collections.singleton(this);
	}

	private String renderWithoutChildren(Cache cache, StructType cast) {
		String formatted = format();
		String header = cast.renderHeader(implName());
		String footer = content.render(cache);
		cache.append(1, header + footer);
		return implName() + formatted;
	}

	private String renderWithChildren(Cache cache, StructType cast, Collection<Node> structures) {
		String name = actualName();
		Type type = new NativeStructType(name);
		structures.stream()
				.map(SimpleStructNode.class::cast)
				.forEach(node -> appendScope(cast, name, node));
		cache.append(STRUCT, cast.renderStruct(name));
		String header = cast.renderHeader(implName());
		String footer = renderFooter(cache, cast, name, type);
		cache.append(CONTENT, header + footer);
		return implName();
	}

	private String format() {
		if (1 < names.size()) {
			String parent = names.get(names.size() - 2);
			String child = names.get(names.size() - 1);
			return appendField(parent, child).toString();
		} else {
			return "";
		}
	}

	private String implName() {
		if (names.isEmpty()) {
			throw new IllegalStateException("Names is empty");
		} else if (1 == names.size() && "main".equals(names.get(0))) {
			return "main";
		} else {
			String join = String.join("_", names);
			return join + "_";
		}
	}

	@Override
	public String actualName() {
		if (names.isEmpty()) throw new IllegalStateException("No names provided.");
		return names.get(names.size() - 1);
	}

	@Override
	public StructType type() {
		return type;
	}

	private void appendScope(StructType cast, String name, StructNode node) {
		StructType type = node.type();
		type.appendParameter(name, PointerType.ANY);
		cast.appendChild(node.actualName(), type);
	}

	private String renderFooter(Cache cache, StructType cast, String name, Type type) {
		Node construction = cast.renderConstruction();
		Node declaration = new DeclareNode(name, type, construction);
		ParentNode contentToRender = prepareContent(declaration);
		return contentToRender.render(cache);
	}

	private CharSequence appendField(String parent, String child) {
		return new StringBuilder()
				.append(";")
				.append(parent)
				.append(".")
				.append(child)
				.append("=")
				.append(child);
	}

	private ParentNode prepareContent(Node declaration) {
		ParentNode contentToRender = content instanceof ParentNode ?
				(ParentNode) content :
				new BlockNode(content);
		List<Node> children = contentToRender.children();
		children.add(0, declaration);
		return contentToRender;
	}
}
