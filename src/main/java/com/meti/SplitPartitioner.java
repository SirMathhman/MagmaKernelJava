package com.meti;

import java.util.ArrayList;
import java.util.List;

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
		int depth = 0;
		char[] charArray = content.toCharArray();
		int length = charArray.length;
		for (int i = 0; i < length; i++) {
			char c = charArray[i];
			if (c == value && 0 == depth) {
				list.add(i);
			} else {
				if (c == open) depth++;
				if (c == close) depth--;
			}
		}
		return list;
	}

	@Override
	public List<String> toPartitions(String content) {
		List<String> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		int depth = 0;
		for (char c : content.toCharArray()) {
			if (c == value && 0 == depth) {
				list.add(builder.toString());
				builder = new StringBuilder();
			} else {
				if (c == open) depth++;
				if (c == close) depth--;
			}
		}
		list.add(builder.toString());
		list.removeIf(String::isBlank);
		return list;
	}
}
