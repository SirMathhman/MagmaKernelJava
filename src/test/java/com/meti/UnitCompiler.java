package com.meti;

public class UnitCompiler implements Compiler {
	private final Unit root;

	public UnitCompiler(Unit root) {
		this.root = root;
	}

	@Override
	public Node compile(String value) {
		return root.compile(value, this).orElseThrow();
	}

	@Override
	public Type resolve(String value) {
		return root.resolve(value, this).orElseThrow();
	}
}