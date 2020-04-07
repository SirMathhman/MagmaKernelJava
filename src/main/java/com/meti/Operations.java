package com.meti;

public interface Operations {
	Renderable toInstance(Renderable value0, Renderable value1);

	enum MathOperations implements Operations {
		ADD("+");

		private final String operation;

		MathOperations(String operation) {
			this.operation = operation;
		}

		@Override
		public Renderable toInstance(Renderable value0, Renderable value1) {
			return new Operation(operation, value0, value1);
		}
	}
}
