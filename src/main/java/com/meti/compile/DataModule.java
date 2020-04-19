package com.meti.compile;

import com.google.inject.AbstractModule;

public class DataModule extends AbstractModule {
	private final Cache cache = new Cache();
	private final Headers headers;
	private final Stack stack = new Stack();

	public DataModule() {
		this(new Headers());
	}

	public DataModule(Headers headers) {
		this.headers = headers;
	}

	@Override
	protected void configure() {
		bind(Cache.class).toInstance(cache);
		bind(Stack.class).toInstance(stack);
		bind(Headers.class).toInstance(headers);
	}
}
