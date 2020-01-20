package com.meti.declare;

import com.meti.node.Node;
import com.meti.node.Type;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Declarations {
	Declaration absolute(Collection<String> values);

	Declaration current();

	Node define(String name, Type type, Supplier<? extends Node> action);

	void define(String name, Type type);

	void define(String name, Type type, Runnable action);

	void defineParameter(String name, Type type);

	Optional<Declaration> relative(String value);

	Declaration root();

	Stream<Declaration> stream();
}
