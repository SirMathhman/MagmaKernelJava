package com.meti;

import java.util.List;

public interface Partitioner {
	List<Integer> toIndices(String content);

	List<String> toPartitions(String content);
}
