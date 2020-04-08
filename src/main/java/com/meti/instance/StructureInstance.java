package com.meti.instance;

import com.meti.Instance;
import com.meti.type.FunctionType;
import com.meti.type.Type;

import java.util.List;
import java.util.stream.Collectors;

public class StructureInstance implements Instance {
	private final List<? extends Instance> parameters;
	private final Instance returnType;

	public StructureInstance(Instance returnType, List<? extends Instance> parameters) {
		this.returnType = returnType;
		this.parameters = parameters;
	}

	@Override
	public Type toType() {
		List<Type> types = parameters.stream()
				.map(Instance::toType)
				.collect(Collectors.toList());
		return new FunctionType(returnType.toType(), types);
	}
}
