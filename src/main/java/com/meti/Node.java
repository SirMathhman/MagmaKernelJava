package com.meti;

import java.util.Collection;

public interface Node {
	Collection<? extends Renderable> render();
}
