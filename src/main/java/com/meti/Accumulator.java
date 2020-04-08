package com.meti;

import java.util.Optional;

public class Accumulator {
	private Instance instance;
	private String string;

	public Optional<String> name() {
		return Optional.ofNullable(string);
	}

	public Optional<Instance> peek() {
		return Optional.ofNullable(instance);
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public void setString(String string) {
		this.string = string;
	}
}
