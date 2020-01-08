package com.meti;

import java.util.List;

public class MagmaCompiler {
	private final StringBuilder callback = new StringBuilder();
	private final Declarations declarations = new Declarations();
	protected final Compiler compiler = new UnitCompiler(List.of(
			new ArrayUnit(),
			new AnyUnit(),
			new VarArgsUnit(),
			new PointerUnit(),
			new StringUnit(),
			new ReturnUnit(),
			new CastUnit(),
			new VoidUnit(),
			new EmptyUnit(),
			new CharUnit(),
			new BlockUnit(),
			new OperationUnit(),
			new DeclareUnit(callback, declarations),
			new StructUnit(callback, declarations),
			new AssignUnit(),
			new IntUnit(),
			new ArrayIndexUnit(),
			new InvocationUnit(callback),
			new VariableUnit(declarations)));

	protected String compileAll(String value) {
		String result = compiler.compileAll(value);
		return callback + result;
	}

	protected String compileOnly(String value) {
		String result = compiler.compileOnly(value);
		return callback + result;
	}
}
