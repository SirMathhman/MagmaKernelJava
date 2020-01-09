package com.meti.unit;

import com.meti.compile.ComplexCompiler;
import com.meti.type.BuildableType;
import com.meti.type.Type;

import java.util.Optional;

public class VarArgsUnit implements CompoundUnit {
    @Override
    public boolean canCompile(String value) {
        return false;
    }

    @Override
    public String compile(String value, ComplexCompiler compiler) {
        return null;
    }

    @Override
    public Optional<? extends Type> resolveName(String value, ComplexCompiler compiler) {
        if (value.endsWith("...")) {
            int index = value.indexOf('.');
            Type child = compiler.resolveName(value.substring(0, index));
            return Optional.of(new BuildableType(".", child));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Type> resolveValue(String value, ComplexCompiler compiler) {
        return Optional.empty();
    }
}
