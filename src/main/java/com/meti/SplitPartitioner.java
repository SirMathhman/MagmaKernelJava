package com.meti;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class SplitPartitioner implements Partitioner {
	private final char close;
	private final char open;
	private final char value;

	public SplitPartitioner(char value, char open, char close) {
		this.value = value;
		this.open = open;
		this.close = close;
	}

	@Override
	public List<Integer> toIndices(String content) {
		List<Integer> list = new ArrayList<>();
		parse(content,
				(index, character) -> list.add(index),
				(index, character) -> {});
		return list;
	}

	private void parse(String content, BiConsumer<Integer, Character> valid, BiConsumer<Integer, Character> invalid) {
		int depth = 0;
		char[] charArray = content.toCharArray();
		int length = charArray.length;
		for (int index = 0; index < length; index++) {
			char character = charArray[index];
			if (character == value && 0 == depth) {
				valid.accept(index, character);
			} else {
				if (character == open) depth++;
				if (character == close) depth--;
				invalid.accept(index, character);
			}
		}
	}

	@Override
	public List<String> toPartitions(String content) {
		List<String> list = new ArrayList<>();
		List<Character> charList = new ArrayList<>();
		parse(content,
				(index, character) -> {
					list.add(listToString(charList));
					charList.clear();
				},
				(index, character) -> charList.add(character));
		list.add(listToString(charList));
		list.removeIf(String::isBlank);
		return list;
	}

	private String listToString(List<Character> charList) {
		return charList.stream()
				.map(String::valueOf)
				.collect(Collectors.joining());
	}
}
