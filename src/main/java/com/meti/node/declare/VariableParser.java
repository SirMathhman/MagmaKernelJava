package com.meti.node.declare;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.exception.ParseException;
import com.meti.node.Node;
import com.meti.node.Type;
import com.meti.node.struct.FieldNode;
import com.meti.node.struct.type.FunctionType;
import com.meti.node.struct.type.StructType;
import com.meti.parse.Declaration;
import com.meti.parse.Declarations;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class VariableParser implements Parser {
	private final Declarations declarations;

	public VariableParser(Declarations declarations) {
		this.declarations = declarations;
	}

	private Optional<Node> buildNode(boolean isField, Declaration parent, String child, StructType type) {
		return isField ?
				buildVariable(parent, child, type) :
				buildVariable(child);
	}

	private Optional<Node> buildVariable(Declaration parent, String childName, StructType type) {
		Declaration child = parent.child(childName).orElseThrow(() -> throwChildNotDefined(parent, childName));
		Node node = child.isFunctional() ?
				new VariableNode(child.joinStack()) :
				new FieldNode(parent, childName);
		return Optional.of(node);
	}

	private Optional<Node> buildVariable(String trim) {
		String name = declarations.relative(trim)
				.filter(Declaration::isFunctional)
				.map(Declaration::joinStack)
				.orElse(trim);
		Node node = new VariableNode(name);
		return Optional.of(node);
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		String trim = content.trim();
		return trim.contains(".") ?
				parseAccessor(compiler, trim) :
				parseSimple(trim);
	}

	private Optional<Node> parseAccessor(Compiler compiler, String trim) {
		String parent = trim.substring(0, trim.lastIndexOf('.'));
		String child = trim.substring(trim.lastIndexOf('.') + 1);
		Type parentType = compiler.resolveValue(parent);
		return parentType instanceof StructType ?
				parseObject(child, (StructType) parentType) :
				parseSingleton(compiler, parent, child, parentType);
	}

	private Optional<Node> parseChild(String childName) {
		Declaration current = declarations.current();
		return !current.hasChild(childName) || declarations.isRoot(current) || current.hasParameter(childName) ?
				parseCurrentChild(childName) :
				buildNode(current.isSuperStructure(), current, childName, null);
	}

	private Optional<Node> parseCurrentChild(String childName) {
		Optional<Declaration> child = declarations.relative(childName);
		Declaration relative = child.orElseThrow(() -> new ParseException(childName + " is not defined."));
		return buildNode(relative.isParameter(), declarations.parent(childName).orElseThrow(), childName, null);
	}

	private Optional<Node> parseObject(String child, StructType parentType) {
		Declaration declaration = parentType.declaration();
		return buildVariable(declaration, child, null);
	}

	private Optional<Node> parseParameter(String childName) {
		return declarations.parent(childName)
				.filter(declaration -> !declarations.isRoot(declaration))
				.filter(declaration -> declaration.hasParameter(childName))
				.flatMap(declaration -> buildNode(declaration.isSuperStructure(), declaration, childName, null));
	}

	private Optional<Node> parseReference(Compiler compiler, String before, String after) {
		Type singletonType = compiler.resolveValue("_" + before);
		if (singletonType instanceof StructType) {
			return parseObject(after, (StructType) singletonType);
		} else {
			throw new ParseException(before + " is not an instance of a singleton.");
		}
	}

	private Optional<Node> parseSimple(String trim) {
		Optional<Node> parent = parseParameter(trim);
		return parent.isPresent() ? parent : parseChild(trim);
	}

	private Optional<Node> parseSingleton(Compiler compiler, String before, String after, Type parentType) {
		if (parentType instanceof FunctionType) {
			return parseReference(compiler, before, after);
		} else {
			throw new ParseException(before + " is not an reference to a singleton.");
		}
	}

	private RuntimeException throwChildNotDefined(Declaration parent, String childName) {
		String parentName = parent.name();
		String formattedParentName = (parentName.endsWith("$")) ?
				parentName.substring(0, parentName.length() - 1) :
				parentName;
		StringBuilder builder = new StringBuilder()
				.append(formattedParentName)
				.append(".")
				.append(childName)
				.append(" is not defined.");
		if (!parent.children().isEmpty()) {
			String collect = parent.children()
					.stream()
					.map(Declaration::name)
					.filter(s -> !s.equals(formattedParentName))
					.sorted(Comparator.comparingInt(o -> o.toString().compareTo(childName)).reversed())
					.limit(3)
					.collect(Collectors.joining(" or "));
			builder.append(" Perhaps you meant ")
					.append(collect)
					.append(".");
		}
		return new ParseException(builder.toString());
	}
}
