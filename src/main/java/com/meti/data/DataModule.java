package com.meti.data;

import com.google.inject.AbstractModule;

import java.util.HashMap;

public class DataModule extends AbstractModule {
	private static final Register REGISTER = new MappedRegister(new HashMap<String, Object>());
	private static final Stack STACK = new MappedStack();

	@Override
	protected void configure() {
		bind(Register.class).toInstance(REGISTER);
		bind(Stack.class).toInstance(STACK);
	}
}
