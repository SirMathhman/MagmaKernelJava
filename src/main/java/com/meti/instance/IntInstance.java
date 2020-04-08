package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PrimitiveType;
import com.meti.type.Type;

public class IntInstance implements Instance {
	public static final Instance INSTANCE = new IntInstance();

	private IntInstance() {
	}

	@Override
	public Type toType() {
		return PrimitiveType.INT;
	}
}
