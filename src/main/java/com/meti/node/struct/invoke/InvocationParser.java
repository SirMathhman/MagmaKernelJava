package com.meti.node.struct.invoke;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.exception.RenderException;
import com.meti.node.Node;
import com.meti.node.Type;
import com.meti.node.declare.CVariableNode;
import com.meti.node.struct.type.FunctionType;
import com.meti.node.struct.type.StructType;
import com.meti.parse.Declaration;
import com.meti.parse.Declarations;
import com.meti.util.ArgumentPartitioner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvocationParser implements Parser {
	private final Declarations declarations;

	public InvocationParser(Declarations declarations) {
		this.declarations = declarations;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		String trim = content.trim();
		if (trim.endsWith(")")) {
			int index = index(trim.substring(0, trim.length() - 1));
			if (index == -1) {
				return Optional.empty();
			}
			String caller = trim.substring(0, index);
			Node callerNode = compiler.parse(caller);
			List<Node> arguments = parseArguments(compiler, trim, caller, callerNode);
			return buildNode(compiler.resolveValue(caller), callerNode, arguments);
		}
		return Optional.empty();
	}

	private int index(String trim) {
		int index = -1;
		int depth = 0;
		char[] charArray = trim.toCharArray();
		for (int i = charArray.length - 1; i >= 0; i--) {
			char c = charArray[i];
			if (c == '(') {
				if (depth == 0) {
					index = i;
					break;
				}
				depth++;
			} else if (c == ')') {
				depth--;
			}
		}
		return index;
	}

	private List<Node> parseArguments(Compiler compiler, String trim, String caller, Node callerNode) {
		return Stream.of(parseGivenArguments(compiler, trim),
				parseParentArguments(compiler, caller, callerNode),
				parseStackArguments(caller))
				.reduce(Stream::concat)
				.map(stream -> stream.collect(Collectors.toList()))
				.orElse(Collections.emptyList());
	}

	private Optional<Node> buildNode(Type type, Node caller, List<? extends Node> arguments) {
		if (type instanceof FunctionType) {
			Type returnType = ((FunctionType) type).returnType();
			Node node = buildNodeFromReturnType(caller, arguments, returnType);
			return Optional.of(node);
		} else {
			throw new RenderException(type + " is not a function.");
		}
	}

	private Stream<Node> parseGivenArguments(Compiler compiler, String trim) {
		int index = -1;
		String subString = trim.substring(index(trim.substring(0, trim.length() - 1)) + 1);
		int depth = 0;
		char[] charArray = subString.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (c == '(') {
				depth++;
			} else if (c == ')') {
				if (depth == 0) {
					index = i;
					break;
				}
				depth--;
			}
		}

		String values = subString.substring(0, index);
		return new ArgumentPartitioner(values)
				.partition()
				.stream()
				.filter(s -> !s.isBlank())
				.map(compiler::parse);
	}

	private Stream<Node> parseParentArguments(Compiler compiler, String caller, Node callerNode) {
		if (caller.contains(".")) {
			String parent = caller.substring(0, caller.lastIndexOf('.')).trim();
			Optional<Node> singleton = checkSingleton(parent);
			if (singleton.isEmpty()) {
				return checkClass(parent, compiler).stream();
			} else {
				return singleton.stream();
			}
		}
		return Stream.empty();
	}

	private Stream<Node> parseStackArguments(String caller) {
		return declarations.relative(caller)
				.map(Declaration::toParentParameters)
				.stream()
				.flatMap(Collection::stream);
	}

	private Node buildNodeFromReturnType(Node callerNode, List<? extends Node> arguments, Type returnType) {
		return new Invocation(returnType, callerNode, arguments);
	}

	private Optional<Node> checkSingleton(String parent) {
		return declarations.relative(parent + "$")
				.flatMap(declaration -> declaration.child(parent))
				.map(declaration -> new CVariableNode(parent));
	}

	private Optional<Node> checkClass(String parent, Compiler compiler) {
		Type parentType = compiler.resolveValue(parent);
		if (parentType instanceof StructType) {
			Node parentNode = compiler.parse(parent);
			return Optional.of(parentNode);
		} else {
			return Optional.empty();
		}
	}
}
