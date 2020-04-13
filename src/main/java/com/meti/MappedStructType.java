package com.meti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MappedStructType implements StructType {
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
	public void appendParameter(String name, Type type) {
		if (parameters.containsKey(name)) throw new IllegalArgumentException(name + " is already defined.");
		parameters.put(name, type);
	}

	@Override
	public Node renderConstruction() {
		List<VariableNode> lists = parameters.keySet()
				.stream()
				.map(VariableNode::new)
				.collect(Collectors.toList());
		return new ConstructNode(lists);
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

	private String renderStructFields() {
		return parameters.keySet()
				.stream()
				.map(s -> parameters.get(s).render(s))
				.map(s -> s + ";")
				.collect(Collectors.joining("", "{", "}"));
	}

	private String renderHeaderParams() {
		return parameters.keySet()
				.stream()
				.map(s -> parameters.get(s).render(s))
				.collect(Collectors.joining(",", "(", ")"));
	}

	@Override
	public String render(String name) {
		String paramString = renderParams();
		return returnType.render("(*" + name + ")" + paramString);
	}

	private String renderParams() {
		return parameters.values()
				.stream()
				.map(Type::render)
				.map(String::trim)
				.collect(Collectors.joining(",", "(", ")"));
	}
}
