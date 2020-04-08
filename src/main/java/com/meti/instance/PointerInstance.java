package com.meti.instance;

import com.meti.Instance;
import com.meti.type.PointerType;
import com.meti.type.Type;

public class PointerInstance implements Instance {
	private final Instance child;

	public PointerInstance(Instance child) {
		this.child = child;
	}

	@Override
	public Type toType() {
		return new PointerType(child.toType());
	}
}
