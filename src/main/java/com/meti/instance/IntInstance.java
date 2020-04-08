package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PrimitiveType;
import com.meti.type.Type;

public class IntInstance implements Instance {
	@Override
	public Type toType() {
		return PrimitiveType.INT;
	}
}
