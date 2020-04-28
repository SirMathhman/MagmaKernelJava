package com.meti.data;

import com.meti.resolve.Instance;

import java.util.Collection;
import java.util.Optional;

public interface DataStack {
	Collection<String> asSnapshot();

	void enter(String name, Instance instance);

	void exit();

	Optional<Instance> get(String name);

	boolean hasParent(Collection<String> names);
}
