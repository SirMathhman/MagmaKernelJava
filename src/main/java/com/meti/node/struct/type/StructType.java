package com.meti.node.struct.type;

import com.meti.node.Node;
import com.meti.node.Type;
import com.meti.parse.Declaration;

import java.util.Optional;

public interface StructType extends Type {
	Node bind(String instanceName, String child);

	Declaration declaration();

	@Override
	default boolean isInstanceOf(Type type) {
		return false;
	}

	Optional<Type> typeOf(String child);
}
