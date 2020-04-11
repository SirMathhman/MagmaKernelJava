package com.meti;

public abstract class SimpleNode implements Node {
	@Override
	public boolean hasMultiple() {
		return false;
	}

	@Override
	public boolean hasStructure() {
		return false;
	}
}
