package com.meti.resolve;

import java.util.Map;
import java.util.stream.Collectors;

public interface BlockInstanceBuilder {
	static BlockInstanceBuilder create() {
		return new BlockInstanceBuilderImpl();
	}

	BlockInstance build();

	BlockInstanceBuilder withParameters(Map<String, Instance> parameters);

	BlockInstanceBuilder withReturnInstance(Instance instance);

	final class BlockInstanceBuilderImpl implements BlockInstanceBuilder {
		private final Map<String, Instance> parameters;
		private final Instance returnInstance;

		public BlockInstanceBuilderImpl() {
			this(null, null);
		}

		public BlockInstanceBuilderImpl(Map<String, Instance> parameters, Instance returnInstance) {
			this.parameters = parameters;
			this.returnInstance = returnInstance;
		}

		@Override
		public BlockInstance build() {
			return new BuiltBlockInstance(parameters, returnInstance);
		}

		@Override
		public BlockInstanceBuilder withParameters(Map<String, Instance> parameters) {
			return new BlockInstanceBuilderImpl(parameters, returnInstance);
		}

		@Override
		public BlockInstanceBuilder withReturnInstance(Instance instance) {
			return new BlockInstanceBuilderImpl(parameters, instance);
		}
	}

	final class BuiltBlockInstance implements BlockInstance {
		private final Map<String, Instance> parameters;
		private final Instance returnInstance;

		public BuiltBlockInstance(Map<String, Instance> parameters, Instance returnInstance) {
			this.parameters = parameters;
			this.returnInstance = returnInstance;
		}

		@Override
		public Type build() {
			return name -> {
				String result = parameters.keySet()
						.stream()
						.map(s -> parameters.get(s)
								.build()
								.render(s))
						.collect(Collectors.joining(",", "(", ")"));
				return returnInstance.build().render("(* " + name + ")" + result);
			};
		}
	}
}
