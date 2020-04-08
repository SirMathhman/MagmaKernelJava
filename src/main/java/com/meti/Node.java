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

	default Renderable renderSingle() {
		Renderable[] array = render().toArray(Renderable[]::new);
		if (1 == array.length) return array[0];
		throw new IllegalStateException("Not a singleton.");
	}
}
