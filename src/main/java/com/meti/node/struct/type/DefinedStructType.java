package com.meti.node.struct.type;

import com.meti.exception.ParseException;
import com.meti.node.Node;
import com.meti.node.Type;
import com.meti.node.ValueType;
import com.meti.node.declare.CVariableNode;
import com.meti.parse.Declaration;

import java.util.Optional;

public class DefinedStructType extends ValueType implements StructType {
	private final Declaration declaration;

	public DefinedStructType(Declaration declaration) {
		this.declaration = declaration;
	}

	@Override
	public Node bind(String instanceName, String child) {
		if (declaration().hasChild(child)) {
			return new CVariableNode(instanceName);
		} else {
			throw new ParseException(instanceName + "." + child + " is not defined.");
		}
	}

	@Override
	public Declaration declaration() {
		return declaration;
	}

	@Override
	public Optional<Type> typeOf(String child) {
		return declaration.child(child).map(Declaration::type);
	}

	@Override
	public boolean isInstanceOf(Type type) {
		if (!(type instanceof StructType)) return false;
		return declaration.equals(((StructType) type).declaration());
	}

	@Override
	public String render() {
		return new NativeStructType(declaration.name()).render();
	}

	@Override
	public String render(String name) {
		return new NativeStructType(declaration.name()).render(name);
	}

	@Override
	public String toMagmaString() {
		return "";
	}
}
