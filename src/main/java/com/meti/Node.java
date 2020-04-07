package com.meti;

import com.meti.render.Renderable;

import java.util.Collection;

public interface Node {
	Collection<? extends Renderable> render();
}
