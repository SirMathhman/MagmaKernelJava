package com.meti;

import java.util.Collection;

public interface Cache {
	Cache apply(CacheUpdate update);

	Collection<String> list();

	String render(String identifier);
}
