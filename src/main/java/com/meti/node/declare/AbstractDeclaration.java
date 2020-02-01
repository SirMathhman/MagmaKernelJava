package com.meti.node.declare;

import com.meti.node.Node;
import com.meti.node.Parameter;
import com.meti.node.Type;
import com.meti.node.struct.FunctionType;
import com.meti.node.struct.StructNode;
import com.meti.node.struct.StructType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbstractDeclaration implements Declaration {
	private final List<Declaration> children = new ArrayList<>();
	private final String name;
	private final Type type;

	AbstractDeclaration(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Optional<Declaration> child(String name) {
		return children.stream()
				.filter(declaration -> declaration.matches(name))
				.findFirst();
	}

	@Override
	public List<Declaration> children() {
		return children;
	}

	@Override
	public Node declareInstance() {
		return new DeclareNode(new StructType(name),
				tempName(), new VariableNode("{" + joinArgs() + "}"));
	}

	private List<Parameter> childrenAsParams() {
		return children().stream()
				.filter(child -> !child.isFunction())
				.map(Declaration::toParameter)
				.collect(Collectors.toList());
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public boolean isFunction() {
		return type instanceof FunctionType;
	}

	@Override
	public String joinArgs() {
		return childrenAsParams().stream()
				.map(Parameter::name)
				.collect(Collectors.joining(","));
	}

	@Override
	public boolean matches(String name) {
		return this.name.equals(name);
	}

	@Override
	public String tempName() {
		return name + "_";
	}

	@Override
	public Parameter toParameter() {
		return Parameter.create(type, name);
	}

	@Override
	public Declaration define(Type type, String name) {
		Declaration declaration = new AbstractDeclaration(name, type);
		children.add(declaration);
		return declaration;
	}

	@Override
	public Declaration define(Parameter parameter) {
		Declaration declaration = parameter.toDeclaration();
		children.add(declaration);
		return declaration;
	}

	@Override
	public String instanceName() {
		return name + "_";
	}

	@Override
	public boolean isParent() {
		return children.stream().anyMatch(Declaration::isFunction);
	}

	@Override
	public Node toStruct() {
		return new StructNode(name, childrenAsParams());
	}

	@Override
	public Node toVariable() {
		return new VariableNode(name);
	}

	@Override
	public Type type() {
		return type;
	}
}
