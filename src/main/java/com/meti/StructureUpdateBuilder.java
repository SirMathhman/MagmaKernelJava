package com.meti;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface StructureUpdateBuilder {
	static StructureUpdateBuilder create() {
		return new StructureUpdateBuilderImpl();
	}

	CacheUpdate build();

	StructureUpdateBuilder withField(String name, Type type);

	StructureUpdateBuilder withName(String name);

	class StructureUpdateBuilderImpl implements StructureUpdateBuilder {
		private final Map<String, Type> fields;
		private final String name;

		private StructureUpdateBuilderImpl() {
			this(null, Collections.emptyMap());
		}

		StructureUpdateBuilderImpl(String name, Map<String, Type> fields) {
			this.name = name;
			this.fields = fields;
		}

		@Override
		public CacheUpdate build() {
			return new StructureUpdate(name, fields);
		}

		@Override
		public StructureUpdateBuilder withField(String name, Type type) {
			Map<String, Type> copy = new HashMap<>(fields);
			if (copy.containsKey(name)) throw new IllegalArgumentException(name + " is already defined.");
			copy.put(name, type);
			return new StructureUpdateBuilderImpl(this.name, copy);
		}

		@Override
		public StructureUpdateBuilder withName(String name) {
			return new StructureUpdateBuilderImpl(name, new HashMap<>(fields));
		}
	}
}
