package com.meti.compile;

import com.google.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DeclareParser implements Parser {
	private final Cache cache;

	@Inject
	public DeclareParser(Cache cache) {
		this.cache = cache;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int equals = content.indexOf('=');
		if (-1 == equals) {
			return build(compiler, content, this::buildWithoutInit, null);
		} else {
			String before = content.substring(0, equals).trim();
			String after = content.substring(equals + 1).trim();
			return build(compiler, before,
					(name, type) -> buildWithInit(compiler, name, type, after),
					() -> compiler.resolveValue(after));
		}
	}

	private Optional<Node> build(Compiler compiler, String content, BiFunction<String, Type, Node> function,
	                             Supplier<Type> notPresent) {
		int colon = content.indexOf(':');
		String nameString;
		Type type;
		List<DeclareKey> keys;
		String name;
		if (-1 == colon) {
			nameString = content;
			int lastSpace = content.lastIndexOf(' ');
			String keyString = content.substring(0, lastSpace).trim();
			name = nameString.substring(lastSpace + 1).trim();
			keys = parseKeys(keyString);
			if (null == notPresent) {
				return Optional.empty();
			} else {
				type = notPresent.get();
			}
		} else {
			nameString = content.substring(0, colon).trim();
			String typeString = content.substring(colon + 1).trim();
			int lastSpace = nameString.lastIndexOf(' ');
			String keyString = nameString.substring(0, lastSpace).trim();
			name = nameString.substring(lastSpace + 1).trim();
			keys = parseKeys(keyString);
			type = compiler.resolveName(typeString);
		}
		return format(keys, type, name, function);
	}

	private Node buildWithoutInit(String name, Type type) {
		return new DeclareNode(name, type);
	}

	private Node buildWithInit(Compiler compiler, String name, Type type, String value) {
		Node init = compiler.parse(value);
		return new DeclareNode(name, type, init);
	}

	List<DeclareKey> parseKeys(String keyString) {
		return Arrays.stream(keyString.split(" "))
				.filter(s -> !s.isBlank())
				.map(String::toUpperCase)
				.map(this::parseKey)
				.flatMap(Optional::stream)
				.collect(Collectors.toList());
	}

	private Optional<Node> format(List<DeclareKey> keys, Type type, String name,
	                              BiFunction<String, Type, Node> function) {
		if (keys.contains(DeclareKey.VAL) || keys.contains(DeclareKey.VAR)) {
			cache.push(name);
			Node node = function.apply(name, type);
			return keys.contains(DeclareKey.NATIVE) ?
					Optional.of(new EmptyNode()) :
					Optional.of(node);
		} else {
			return Optional.empty();
		}
	}

	private Optional<DeclareKey> parseKey(String value) {
		try {
			return Optional.of(DeclareKey.valueOf(value));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	private enum DeclareKey {
		NATIVE,
		VAL,
		VAR
	}

	private static final class DeclareNode implements Node {
		private final Node init;
		private final String name;
		private final Type type;

		private DeclareNode(String name, Type type) {
			this(name, type, null);
		}

		private DeclareNode(String name, Type type, Node init) {
			this.name = name;
			this.type = type;
			this.init = init;
		}

		@Override
		public String render() {
			StringBuilder builder = new StringBuilder();
			builder.append(type.render(name));
			if (null != init) builder.append("=").append(init.render());
			return builder + ";";
		}
	}
}
