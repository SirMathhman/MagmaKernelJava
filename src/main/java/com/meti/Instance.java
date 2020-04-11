package com.meti;

import java.util.Map;
import java.util.Optional;

public interface Instance {
	Optional<Instance> asReturn();

	Map<String, Instance> children();

	Type toType();
}
