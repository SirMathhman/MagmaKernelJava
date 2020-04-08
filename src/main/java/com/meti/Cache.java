package com.meti;

import java.util.Optional;

public class Cache {
	private Instance instance;

	public Optional<Instance> getInstance() {
		return Optional.ofNullable(instance);
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}
}
