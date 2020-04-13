package com.meti.data;

import com.google.inject.AbstractModule;

public class DataModule extends AbstractModule {
	private static final Register REGISTER = new MappedRegister();
	private static final Stack STACK = new MappedStack();

	@Override
	protected void configure() {
		bind(Register.class).toInstance(REGISTER);
		bind(Stack.class).toInstance(STACK);
	}
}
