package com.meti.node.struct;

import java.util.Optional;

public interface IndexBuffer {
	String cut(int index);

	Optional<String> cutIfPresent(int index);

	boolean isPresent(int index);

	boolean isValid();
}
