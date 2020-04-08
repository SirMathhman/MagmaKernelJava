package com.meti;

import com.google.inject.Binder;
import com.google.inject.Module;

public class DataModule implements Module {
	private static final Accumulator ACCUMULATOR = new Accumulator();
	private final Cache cache;
	private static final Scope SCOPE = new Scope();

	public DataModule(Cache cache) {
		this.cache = cache;
	}

	@Override
	public void configure(Binder binder) {
		binder.bind(Accumulator.class).toInstance(ACCUMULATOR);
		binder.bind(Cache.class).toInstance(cache);
		binder.bind(Scope.class).toInstance(SCOPE);
	}
}
