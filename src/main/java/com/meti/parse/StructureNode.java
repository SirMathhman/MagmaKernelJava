package com.meti.parse;

import com.meti.Instance;
import com.meti.Node;
import com.meti.render.Renderable;
import com.meti.render.block.Function;
import com.meti.type.Type;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class StructureNode implements Node {
	private final Node content;
	private final String name;
	private final Map<String, Instance> parameters;
	private final Instance returnType;

	public StructureNode(String name, Map<String, Instance> parameters, Instance returnType, Node content) {
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
		this.content = content;
	}

	@Override
	public Collection<? extends Renderable> render() {
		Map<String, Type> map = parameters.keySet()
				.stream()
				.collect(Collectors.toMap(java.util.function.Function.identity(), s -> parameters.get(s).toType()));
		return Collections.singleton(new Function(name, returnType.toType(), map, content.renderSingle()));
	}
}
