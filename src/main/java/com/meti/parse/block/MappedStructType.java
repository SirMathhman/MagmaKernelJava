package com.meti.parse.block;

import com.meti.parse.Node;
import com.meti.parse.Type;
import com.meti.parse.store.VariableNode;
import com.meti.parse.util.NullNode;
import com.meti.primitive.PrimitiveType;

import java.util.*;
import java.util.stream.Collectors;

public class MappedStructType implements StructType {
	private final Map<String, Type> children = new HashMap<>();
	private final Map<String, Type> parameters;
	private final Type returnType;

	public MappedStructType(Type returnType) {
		this(returnType, new HashMap<>());
	}

	public MappedStructType(Type returnType, Map<String, Type> parameters) {
		this.returnType = returnType;
		this.parameters = parameters;
	}

	@Override
	public void appendChild(String name, Type type) {
		if (children.containsKey(name)) throw new IllegalArgumentException(name + " is already defined!");
		children.put(name, type);
	}

	@Override
	public void appendParameter(String name, Type type) {
		if (parameters.containsKey(name)) throw new IllegalArgumentException(name + " is already defined.");
		parameters.put(name, type);
	}

	@Override
	public Map<String, Type> parameters() {
		return Collections.unmodifiableMap(parameters);
	}

	@Override
	public Node renderConstruction() {
		List<Node> paramList = parameters.keySet()
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

	private String renderHeaderParams() {
		return parameters.keySet()
				.stream()
				.map(s -> parameters.get(s).render(s))
				.collect(Collectors.joining(",", "(", ")"));
	}

	private String renderParams() {
		return parameters.values()
				.stream()
				.map(Type::render)
				.map(String::trim)
				.collect(Collectors.joining(",", "(", ")"));
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

	private String renderStructFields() {
		String collect = parameters.keySet()
				.stream()
				.sorted()
				.map(s -> parameters.get(s).render(s))
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
