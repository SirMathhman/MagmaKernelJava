package com.meti;

import com.google.inject.Binder;
import com.google.inject.Module;

public class DataModule implements Module {
	private static final Cache CACHE = new Cache();
	private static final Scope SCOPE = new Scope();

	@Override
	public void configure(Binder binder) {
		binder.bind(Cache.class).toInstance(CACHE);
		binder.bind(Scope.class).toInstance(SCOPE);
	}
}
