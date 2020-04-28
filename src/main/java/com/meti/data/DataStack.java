package com.meti.data;

import com.meti.resolve.Instance;

import java.util.List;
import java.util.Optional;

public interface DataStack {
	List<String> asSnapshot();

	void define(String name, Instance instance);

	void enter(String name, Instance instance);

	void exit();

	Optional<Instance> get(String name);

	Optional<Instance> get(List<String> names);

	boolean hasParent(List<String> names);
}
