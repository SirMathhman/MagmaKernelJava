package com.meti.primitive;

import com.meti.Type;

import java.util.Optional;
import java.util.OptionalInt;

public class FloatType implements Type {

	@Override
	public OptionalInt childOrder(String name) {
		return null;
	}

	@Override
	public Optional<Type> childType(String name) {
		return Optional.empty();
	}

	@Override
	public boolean isNamed() {
		return false;
	}

	@Override
    public String render() {
        return "float";
    }

	@Override
	public Optional<Type> returnType() {
		return Optional.empty();
	}

	@Override
	public String render(String name) {
		return null;
	}
}
