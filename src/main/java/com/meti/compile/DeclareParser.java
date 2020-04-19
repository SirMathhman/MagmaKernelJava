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
	private final Stack stack;

	@Inject
	public DeclareParser(Cache cache, Stack stack) {
		this.cache = cache;
		this.stack = stack;
	}

	@Override
	public Optional<Node> parse(String content, Compiler compiler) {
		int equals = -1;
		for (int i = 0; i < content.length() - 2; i++) {
			String pass = content.substring(i, i + 2);
			if (pass.startsWith("=") && !"=>".equals(pass)) {
				equals = i;
			}
		}
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
			if (-1 == lastSpace) {
				return Optional.empty();
			} else {
				String keyString = content.substring(0, lastSpace).trim();
				name = nameString.substring(lastSpace + 1).trim();
				keys = parseKeys(keyString);
				cache.pushName(name);
				if (null == notPresent) {
					return Optional.empty();
				} else {
					type = notPresent.get();
				}
				cache.pushType(type);
			}
		} else {
			nameString = content.substring(0, colon).trim();
			String typeString = content.substring(colon + 1).trim();
			int lastSpace = nameString.lastIndexOf(' ');
			String keyString = nameString.substring(0, lastSpace).trim();
			name = nameString.substring(lastSpace + 1).trim();
			keys = parseKeys(keyString);
			cache.pushName(name);
			type = compiler.resolveName(typeString);
			cache.pushType(type);
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
			stack.enter(name, type);
			Node node = function.apply(name, type);
			stack.exit();
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
