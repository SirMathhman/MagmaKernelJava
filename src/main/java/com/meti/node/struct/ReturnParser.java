package com.meti.node.struct;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.exception.ParseException;
import com.meti.node.Node;
import com.meti.node.Type;
import com.meti.node.struct.type.FunctionType;
import com.meti.parse.Declaration;
import com.meti.parse.Declarations;

import java.util.Optional;

public class ReturnParser implements Parser {
	private final Declarations declarations;

	public ReturnParser(Declarations declarations) {
		this.declarations = declarations;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		return Optional.of(content)
				.map(String::trim)
				.filter(s -> s.startsWith("return "))
				.map(s -> s.substring(7))
				.map(s -> buildNode(s, compiler))
				.map(CReturnNode::new);
	}

	private Node buildNode(String valueString, Compiler compiler) {
		Type type = compiler.resolveValue(valueString);
		Declaration current = declarations.current();
		Type currentType = current.type();
		throwIfNotFunction(currentType);
		throwIfNotInstance(type, (FunctionType) currentType);
		return compiler.parse(valueString);
	}

	private void throwIfNotFunction(Type currentType) {
		if (!(currentType instanceof FunctionType)) {
			throw new ParseException("Return is only allowed in structures.");
		}
	}

	private void throwIfNotInstance(Type type, FunctionType currentType) {
		Type returnType = currentType.returnType();
		if (!type.isInstanceOf(returnType)) {
			throw new ParseException(type + " isn't an instance of return type " + returnType);
		}
	}
}
