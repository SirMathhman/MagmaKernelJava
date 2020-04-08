package com.meti.instance;

import com.meti.Instance;
import com.meti.type.FunctionType;
import com.meti.type.Type;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StructureInstanceImpl implements StructureInstance {
	private final Map<String, Instance> parameters;
	private final Instance returnType;

	public StructureInstanceImpl(Instance returnType, Map<String, Instance> parameters) {
		this.returnType = returnType;
		this.parameters = parameters;
	}

	@Override
	public Map<String, Instance> getParameters() {
		return parameters;
	}

	@Override
	public Instance getReturnType() {
		return returnType;
	}

	@Override
	public Type toType() {
		List<Type> types = parameters.values()
				.stream()
				.map(Instance::toType)
				.collect(Collectors.toList());
		return new FunctionType(returnType.toType(), types);
	}
}
