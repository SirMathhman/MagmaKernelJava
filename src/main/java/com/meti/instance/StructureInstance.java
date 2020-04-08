package com.meti.instance;

import com.meti.Instance;

import java.util.Map;

public interface StructureInstance extends Instance {
	Map<String, Instance> getParameters();

	Instance getReturnType();
}
