package com.meti;

import com.meti.render.Renderable;

import java.util.Collection;
import java.util.stream.Collectors;

public interface Node {
	default String renderJoined() {
		return render().stream()
				.map(Renderable::render)
				.collect(Collectors.joining());
	}

	Collection<? extends Renderable> render();
}
