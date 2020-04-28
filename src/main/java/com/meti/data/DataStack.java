package com.meti.data;

import com.meti.resolve.Instance;

import java.util.Optional;

public interface DataStack {
	void enter(String name, Instance instance);

	void exit();

	Optional<Instance> get(String name);
}
