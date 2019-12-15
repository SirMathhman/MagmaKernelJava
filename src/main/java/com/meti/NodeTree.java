package com.meti;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface NodeTree {
	void append(Node node);

	Optional<Node> locate(Predicate<Node> predicate);

	Optional<Node> locate(List<Node> nodes, Predicate<Node> filter);

	MutableNode locateDeclaration(Node name);

	Optional<Node> locateDeclaration(String... name);
}