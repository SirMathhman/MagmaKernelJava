package com.meti.render.block;

import com.meti.render.Renderable;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class BlockRenderable implements Renderable {
	private final Collection<? extends Renderable> lines;

	public BlockRenderable(Collection<? extends Renderable> lines) {
		this.lines = Collections.unmodifiableCollection(lines);
	}

	@Override
	public String render() {
		return lines.stream()
				.map(Renderable::render)
				.filter(s -> !s.isBlank())
				.map(s -> "\t" + s)
				.collect(Collectors.joining("\n", "{\n", "\n}\n"));
	}
}
