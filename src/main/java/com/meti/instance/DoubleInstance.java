package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PrimitiveType;
import com.meti.type.Type;

public class DoubleInstance implements Instance {
	public static final Instance INSTANCE = new DoubleInstance();

	private DoubleInstance() {
	}

	@Override
	public Type toType() {
		return PrimitiveType.DOUBLE;
	}
}
