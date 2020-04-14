package com.meti.parse.block;

import com.meti.parse.Node;

public interface StructNode extends Node {
	String actualName();

	default StructType type() {
		return type;
	}
}
