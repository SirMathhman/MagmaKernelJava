package com.meti.render.block;

import com.meti.render.Renderable;

import java.util.List;
import java.util.stream.Collectors;

public class Invoke implements Renderable {
	private final String name;
	private final List<? extends Renderable> values;

	public Invoke(String name, List<? extends Renderable> values) {
		this.name = name;
		this.values = values;
	}

	@Override
	public String render() {
		String argString = values.stream()
				.map(Renderable::render)
				.collect(Collectors.joining(",", "(", ")"));
		return name + argString;
	}
}
