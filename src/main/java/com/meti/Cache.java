package com.meti;

public interface Cache {
	Cache apply(CacheUpdate update);

	String render(String identifier);
}
