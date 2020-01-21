package com.meti.node.bracket.struct;

import com.meti.declare.Parameter;
import com.meti.node.Node;
import com.meti.node.Type;
import com.meti.node.other.VoidType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface StructNodeBuilder {
	static StructNodeBuilder create() {
		return new StructNodeBuilderImpl();
	}

	Node create(Generator generator);

	StructNodeBuilder withBlock(Node block);

	StructNodeBuilder withName(String name);

	default StructNodeBuilder withParameters(List<? extends Parameter> parameters) {
		parameters.forEach(this::withParameter);
		return this;
	}

	StructNodeBuilder withParameter(Parameter parameter);

	StructNodeBuilder withReturnType(Type returnType);

	final class StructNodeBuilderImpl implements StructNodeBuilder {
		private final Node block;
		private final String name;
		private final Set<Parameter> parameters;
		private final Type returnType;

		private StructNodeBuilderImpl() {
			this(null, null, new HashSet<>(), null);
		}

		private StructNodeBuilderImpl(Node block, String name, Set<Parameter> parameters, Type returnType) {
			this.block = block;
			this.name = name;
			this.parameters = parameters;
			this.returnType = returnType;
		}

		@Override
		public Node create(Generator generator) {
			String name = buildName(generator);
			Type type = buildType();
			return new StructNode(name, parameters, type, block);
		}

		private String buildName(Generator generator) {
			return (null == this.name) ? generator.next() : this.name;
		}

		private Type buildType() {
			return null == this.returnType ? VoidType.INSTANCE() : this.returnType;
		}

		@Override
		public StructNodeBuilder withBlock(Node block) {
			return new StructNodeBuilderImpl(block, name, parameters, returnType);
		}

		@Override
		public StructNodeBuilder withName(String name) {
			return new StructNodeBuilderImpl(block, name, parameters, returnType);
		}

		@Override
		public StructNodeBuilder withParameter(Parameter parameter) {
			parameters.add(parameter);
			return this;
		}

		@Override
		public StructNodeBuilder withReturnType(Type returnType) {
			return new StructNodeBuilderImpl(block, name, parameters, returnType);
		}
	}
}