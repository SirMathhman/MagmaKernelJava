package com.meti.unit;

import com.google.inject.Inject;
import com.meti.Compiler;
import com.meti.Partitioner;
import com.meti.SplitPartitioner;
import com.meti.Unit;
import com.meti.data.DataStack;
import com.meti.parse.Item;
import com.meti.parse.Node;
import com.meti.resolve.BlockInstanceBuilder;
import com.meti.resolve.Instance;
import com.meti.resolve.Type;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlockUnit implements Unit {
	private static final Partitioner argCloser = new SplitPartitioner(')', '(', ')');
	private static final Partitioner argSplitter = new SplitPartitioner(',', '(', ')');

	private final DataStack stack;
	private int counter = -1;

	@Inject
	public BlockUnit(DataStack stack) {
		this.stack = stack;
	}

	//(x : Int) => Int {}
	@Override
	public Optional<Item> parse(String content, Compiler compiler) {
		if (content.startsWith("(")) {
			List<Integer> indices = argCloser.toIndices(content.substring(1));
			if (1 != indices.size()) return Optional.empty();
			int argClose = indices.get(0) + 1;
			String argString = content.substring(1, argClose).trim();
			Map<String, Instance> parameters = parseParameters(argString, compiler);
			Instance result = parseReturnInstance(content, compiler, argClose);
			String name = name();
			stack.enter(name, BlockInstanceBuilder.create()
					.withParameters(parameters)
					.withReturnInstance(result)
					.build());
			return Optional.of(name)
					.map(Node::supplied)
					.map(Item::supplied);
		}
		return Optional.empty();
	}

	Map<String, Instance> parseParameters(String argString, Compiler compiler) {
		return argSplitter.toPartitions(argString)
				.stream()
				.map(String::trim)
				.collect(Collectors.toMap(s -> {
					int colon = s.indexOf(':');
					return s.substring(0, colon).trim();
				}, s -> {
					int colon = s.indexOf(':');
					String type = s.substring(colon + 1).trim();
					return compiler.resolveName(type);
				}));
	}

	Instance parseReturnInstance(String content, Compiler compiler, int argClose) {
		int bracketBegin = content.indexOf('{');
		String returnString = content.substring(argClose + 1, bracketBegin).trim();
		return parseReturnInstance(returnString, compiler);
	}

	private String name() {
		counter++;
		if (stack.asSnapshot().isEmpty()) {
			return "_" + counter;
		} else {
			return "_" + stack.getName() + counter;
		}
	}

	Instance parseReturnInstance(String returnString, Compiler compiler) {
		Instance result;
		if (returnString.startsWith("=>")) {
			String returnValue = returnString.substring(2).trim();
			result = compiler.resolveName(returnValue);
		} else {
			result = Instance.supplied(Type.supplied("void"));
		}
		return result;
	}

	@Override
	public Optional<Instance> resolveName(String content, Compiler compiler) {
		return Optional.empty();
	}

	@Override
	public Optional<Instance> resolveValue(String content, Compiler compiler) {
		return Optional.empty();
	}
}
