package com.meti;

import java.util.List;

public interface Partitioner {
	List<Integer> toIndices();

	List<String> toPartitions();
}
