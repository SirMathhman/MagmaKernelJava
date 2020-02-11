package com.meti.node.thrower;

import com.meti.Cache;
import com.meti.Compiler;
import com.meti.Parser;
import com.meti.exception.ParseException;
import com.meti.node.*;
import com.meti.node.declare.CAssignNode;
import com.meti.node.declare.CVariableNode;
import com.meti.node.struct.CFunctionNode;
import com.meti.node.struct.CReturnNode;
import com.meti.node.struct.invoke.CInvocationNode;
import com.meti.node.struct.type.FunctionType;
import com.meti.parse.Declarations;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ThrowParser implements Parser {
	private static final CVariableNode THROW = new CVariableNode("_throw");
	private static final CVariableNode THROWABLE = new CVariableNode("throwable");
	private final Cache cache;
	private final Declarations declarations;
	private int counter = 0;

	public ThrowParser(Declarations declarations, Cache cache) {
		this.declarations = declarations;
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		String trim = content.trim();
		if (trim.startsWith("throw ")) {
			String valueString = trim.substring(6);
			Type type = compiler.resolveValue(valueString);
			Node value = compiler.parse(valueString);
			Type innerType = declarations.current().type();
			if (innerType instanceof FunctionType) {
				counter++;
				buildFunction(type, (FunctionType) innerType);
				return Optional.of(buildReturn(value));
			}
		}
		return Optional.empty();
	}

	private void buildFunction(Type type, FunctionType innerType) {
		Type returnType = innerType.returnType();
		Collection<Node> children = buildChildren(returnType);
		Node content = new CContent(children);
		Parameter parameters = Parameter.create(type, "throwable");
		Node node = new CFunctionNode("_throw" + counter, returnType, content, parameters);
		cache.addFunction(node);
	}

	private Node buildReturn(Node value) {
		Node varNode = new CVariableNode("_throw" + counter);
		Node node = new CInvocationNode(varNode, value);
		return new CReturnNode(node);
	}

	private Collection<Node> buildChildren(Type returnType) {
		Node assign = new CAssignNode(THROW, THROWABLE);
		Node returnDefault = getReturnDefault(returnType);
		Node returnNode = new CReturnNode(returnDefault);
		return List.of(assign, returnNode);
	}

	private Node getReturnDefault(Type returnType) {
		Node toReturn;
		if (returnType instanceof DefaultType) {
			toReturn = ((DefaultType) returnType).defaultValue();
		} else {
			throw new ParseException(returnType + " has not default value.");
		}
		return toReturn;
	}
}
