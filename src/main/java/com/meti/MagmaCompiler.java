package com.meti;

import java.util.List;

public class MagmaCompiler {
	private final Declarations declarations = new TreeDeclarations();
	protected final Compiler compiler = new UnitCompiler(List.of(
			new ArrayUnit(),
			new AnyResolver(),
			new VarArgsUnit(),
			new PointerUnit(),
			new StringUnit(),
			new ReturnUnit(),
			new CastUnit(),
			new VoidUnit(),
			new CharUnit(),
			new BlockUnit(),
			new OperationUnit(),
			new DeclareUnit(declarations),
			new StructUnit(declarations),
			new AssignUnit(),
			new IntUnit(),
			new InvocationUnit(),
			new ArrayIndexUnit(),
			new VariableUnit(declarations)));

	protected String compileAll(String value) {
		String result = compiler.compileAll(value);
		return declarations.root().callback() + result;
	}

	protected String compileOnly(String value) {
		String result = compiler.compileOnly(value);
		return declarations.root().callback() + result;
	}
}
