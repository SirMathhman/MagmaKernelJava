package com.meti.node.primitive.ints;

import com.meti.node.Node;
import com.meti.node.Statement;

public class Int implements Statement {
    private final int value;

    public Int(int value) {
        this.value = value;
    }

    @Override
    public Node build() {
        return new CIntNode(value);
    }
}
