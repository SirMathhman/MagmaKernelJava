package com.meti.node.struct.type;

import com.meti.node.Parameter;
import com.meti.node.Type;
import com.meti.node.primitive.special.VoidType;

import java.util.Collection;
import java.util.Collections;

public interface FunctionTypeBuilder {
	static FunctionTypeBuilder create() {
		return new FunctionTypeBuilderImpl();
	}

	FunctionType build();

	FunctionTypeBuilder withName(String name);

	FunctionTypeBuilder withParameters(Collection<? extends Parameter> parameters);

	FunctionTypeBuilder withReturnType(Type returnType);

	class FunctionTypeBuilderImpl implements FunctionTypeBuilder {
		private final String name;
		private final Collection<? extends Parameter> parameters;
		private final Type returnType;

		private FunctionTypeBuilderImpl() {
			this(null, Collections.emptySet(), VoidType.INSTANCE);
		}

		FunctionTypeBuilderImpl(String name, Collection<? extends Parameter> parameters, Type returnType) {
			this.name = name;
			this.parameters = parameters;
			this.returnType = returnType;
		}

		@Override
		public FunctionType build() {
			return new FunctionTypeImpl(parameters, returnType, name);
		}

		@Override
		public FunctionTypeBuilder withName(String name) {
			return new FunctionTypeBuilderImpl(name, parameters, returnType);
		}

		@Override
		public FunctionTypeBuilder withParameters(Collection<? extends Parameter> parameters) {
			return new FunctionTypeBuilderImpl(name, parameters, returnType);
		}

		@Override
		public FunctionTypeBuilder withReturnType(Type returnType) {
			return new FunctionTypeBuilderImpl(name, parameters, returnType);
		}
	}
}
