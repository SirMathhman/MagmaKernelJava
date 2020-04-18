package com.meti.compile;

import com.google.inject.AbstractModule;

public class DataModule extends AbstractModule {
	private final Cache cache = new Cache();

	@Override
	protected void configure() {
		bind(Cache.class).toInstance(cache);
	}
}
