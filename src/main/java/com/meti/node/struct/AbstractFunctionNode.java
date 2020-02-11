package com.meti.node.struct;

import com.meti.node.Node;
import com.meti.node.Parameter;
import com.meti.node.Type;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractFunctionNode implements Node {
	private final String name;
	private final Collection<? extends Parameter> parameters;
	private final Type returnType;

	public AbstractFunctionNode(String name, Type returnType, Parameter... parameters) {
		this(name, returnType, List.of(parameters));
	}

	public AbstractFunctionNode(String name, Type returnType, Collection<? extends Parameter> parameters) {
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
	}

	@Override
	public String render() {
		return returnType.render(name) +
		       "(" + renderParams() + ");";
	}

	private String renderParams() {
		return parameters.stream()
				.map(Parameter::render)
				.collect(Collectors.joining(","));
	}
}
