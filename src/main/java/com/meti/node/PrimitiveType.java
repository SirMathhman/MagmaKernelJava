package com.meti.node;

import com.meti.node.other.VoidType;
import com.meti.node.value.compound.variable.FieldNodeBuilder;

import java.util.Optional;
import java.util.OptionalInt;

public abstract class PrimitiveType implements Type {
	@Override
	public Optional<Node> toField(Node instance, String name) {
		Optional<Type> child = childType(name.trim());
		OptionalInt order = childOrder(name.trim());
		Node field =
				FieldNodeBuilder.create().withInstanceArray(instance).withOrder(order.orElseThrow()).withType(child.orElseThrow()).withName(name).build();
		return Optional.of(field);
	}

	private OptionalInt childOrder(String childName) {
		return OptionalInt.empty();
	}

	@Override
	public Optional<Type> childType(String childName) {
		return Optional.empty();
	}

	@Override
	public boolean isNamed() {
		return false;
	}

	@Override
	public String renderWithName(String name) {
		return (isNamed()) ? render() : render() + " " + name;
	}

	@Override
    public Optional<Type> returnType() {
        return Optional.empty();
    }

	@Override
	public boolean doesReturnVoid() {
		return returnType().isPresent() && returnType().get() instanceof VoidType;
	}
}