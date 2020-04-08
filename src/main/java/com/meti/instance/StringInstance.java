package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PointerType;
import com.meti.type.PrimitiveType;
import com.meti.type.Type;

public class StringInstance implements Instance {
	private static final Type INSTANCE = new PointerType(PrimitiveType.CHAR);

	@Override
	public Type toType() {
		return INSTANCE;
	}
}
