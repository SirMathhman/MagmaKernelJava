package com.meti;

import java.util.Collection;
import java.util.Optional;

public interface CacheUpdate {
	Optional<String> identifier();

	String render(Collection<String> lines);
}
