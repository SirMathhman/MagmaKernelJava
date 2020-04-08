package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PrimitiveType;
import com.meti.type.Type;

public class CharInstance implements Instance {
	public static final Instance INSTANCE = new CharInstance();

	private CharInstance() {
	}

	@Override
	public Type toType() {
		return PrimitiveType.CHAR;
	}
}
