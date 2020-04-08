package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PrimitiveType;
import com.meti.type.Type;

public class VoidInstance implements Instance {
	public static final Instance INSTANCE = new VoidInstance();

	private VoidInstance() {
	}

	@Override
	public Type toType() {
		return PrimitiveType.VOID;
	}
}
