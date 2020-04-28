package com.meti.data;

import com.meti.resolve.Instance;

import java.util.Optional;

public interface DataScope {
	Optional<DataScope> get(String name);

	Instance getInstance();

	String getName();

	Optional<DataScope> getParent();

	DataScope set(String name, Instance instance);
}
