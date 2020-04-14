package com.meti.parse.block;

import com.meti.parse.Node;

public interface StructNode extends Node {
	String actualName();

	StructType type();
}
