package com.meti.unit.value;

import com.meti.Compiler;
import com.meti.type.Type;
import com.meti.type.TypeStack;
import com.meti.unit.Data;
import com.meti.unit.Unit;

import java.util.Optional;

import static com.meti.type.PrimitiveType.*;

public class PrimitiveUnit implements Unit {
	private final TypeStack stack;

	public PrimitiveUnit(Data data) {
		this.stack = data.getTypeStack();
	}

	@Override
	public Optional<String> parse(String input, Compiler compiler) {
		String trimmedInput = input.trim();
		if (trimmedInput.equals("true") || trimmedInput.equals("false")) {
			return Optional.of(trimmedInput);
		}
		try {
			Integer.parseInt(trimmedInput);
			stack.add(INT);
			return Optional.of(trimmedInput);
		} catch (NumberFormatException e) {
			try {
				Double.parseDouble(trimmedInput);
				stack.add(DOUBLE);
				return Optional.of(trimmedInput);
			} catch (NumberFormatException e1) {
				return Optional.empty();
			}
		}
	}

	@Override
	public Optional<Type> resolve(String input, Compiler compiler) {
		if (input.equals("void")) {
			return Optional.of(VOID);
		} else if (input.equals("int")) {
			return Optional.of(INT);
		}
		return Optional.empty();
	}
}