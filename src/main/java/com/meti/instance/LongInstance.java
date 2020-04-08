package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PrimitiveType;
import com.meti.type.Type;

public class LongInstance implements Instance {
	@Override
	public Type toType() {
		return PrimitiveType.LONG;
	}
}
