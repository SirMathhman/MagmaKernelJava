package com.meti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MappedStructType implements StructType {
	private final Map<String, Type> children = new HashMap<>();
	private final Map<String, Type> fields;
	private final Type returnType;

	public MappedStructType(Type returnType) {
		this(returnType, new HashMap<>());
	}

	public MappedStructType(Type returnType, Map<String, Type> fields) {
		this.returnType = returnType;
		this.fields = fields;
	}

	@Override
	public void appendChild(String name, Type type) {
		if (children.containsKey(name)) throw new IllegalArgumentException(name + " is already defined!");
		children.put(name, type);
	}

	@Override
	public void appendParameter(String name, Type type) {
		if (fields.containsKey(name)) throw new IllegalArgumentException(name + " is already defined.");
		fields.put(name, type);
	}

	@Override
	public Node renderConstruction() {
		List<Node> paramList = fields.keySet()
				.stream()
				.sorted()
				.map(VariableNode::new)
				.collect(Collectors.toList());
		List<Node> valueList = children.keySet()
				.stream()
				.sorted()
				.map(children::get)
				.map(Type::defaultValue)
				.collect(Collectors.toList());
		List<Node> joined = new ArrayList<>();
		joined.addAll(paramList);
		joined.addAll(valueList);
		return new ConstructNode(joined);
	}

	@Override
	public String renderHeader(String name) {
		String collect = renderHeaderParams();
		return returnType.render(name + collect);
	}

	@Override
	public String renderStruct(String name) {
		String result = renderStructFields();
		return "struct " + name + result;
	}

	@Override
	public Node defaultValue() {
		return NullNode.INSTANCE;
	}

	@Override
	public String render(String name) {
		String paramString = renderParams();
		return returnType.render("(*" + name + ")" + paramString);
	}

	@Override
	public boolean doesReturnVoid() {
		return returnType.equals(PrimitiveType.VOID);
	}

	private String renderParams() {
		return fields.values()
				.stream()
				.map(Type::render)
				.map(String::trim)
				.collect(Collectors.joining(",", "(", ")"));
	}

	private String renderHeaderParams() {
		return fields.keySet()
				.stream()
				.map(s -> fields.get(s).render(s))
				.collect(Collectors.joining(",", "(", ")"));
	}

	private String renderStructFields() {
		String collect = fields.keySet()
				.stream()
				.sorted()
				.map(s -> fields.get(s).render(s))
				.map(s -> s + ";")
				.collect(Collectors.joining());
		String result = children.keySet()
				.stream()
				.sorted()
				.map(s -> children.get(s).render(s))
				.map(s -> s + ";")
				.collect(Collectors.joining());
		return "{" + collect + result + "};";
	}
}
