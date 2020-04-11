package com.meti;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface FunctionUpdateBuilder {
	static FunctionUpdateBuilder create() {
		return new FunctionUpdateBuilderImpl();
	}

	CacheUpdate build();

	FunctionUpdateBuilder withContent(Node content);

	FunctionUpdateBuilder withName(String name);

	FunctionUpdateBuilder withParameter(String name, Type type);

	FunctionUpdateBuilder withReturnType(Type returnType);

	final class FunctionUpdateBuilderImpl implements FunctionUpdateBuilder {
		private final Node content;
		private final String name;
		private final Map<String, Type> parameters;
		private final Type returnType;

		private FunctionUpdateBuilderImpl() {
			this(null, Collections.emptyMap(), null, null);
		}

		public FunctionUpdateBuilderImpl(String name, Map<String, Type> parameters, Type returnType, Node content) {
			this.name = name;
			this.parameters = parameters;
			this.returnType = returnType;
			this.content = content;
		}

		@Override
		public CacheUpdate build() {
			return new FunctionUpdate(name, parameters, returnType, content);
		}

		@Override
		public FunctionUpdateBuilder withContent(Node content) {
			return new FunctionUpdateBuilderImpl(name, parameters, returnType, content);
		}

		@Override
		public FunctionUpdateBuilder withName(String name) {
			return new FunctionUpdateBuilderImpl(name, parameters, returnType, content);
		}

		@Override
		public FunctionUpdateBuilder withParameter(String name, Type type) {
			Map<String, Type> copy = new HashMap<>(parameters);
			if (copy.containsKey(name)) throw new IllegalArgumentException(name + " is already defined.");
			copy.put(name, type);
			return new FunctionUpdateBuilderImpl(this.name, copy, returnType, content);
		}

		@Override
		public FunctionUpdateBuilder withReturnType(Type returnType) {
			return new FunctionUpdateBuilderImpl(name, parameters, returnType, content);
		}
	}
}
