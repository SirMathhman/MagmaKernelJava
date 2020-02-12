package com.meti.node.declare;

import com.meti.node.Node;
import com.meti.node.Statement;
import com.meti.node.Type;

public class Declare implements Statement {
    private final Type type;
    private final String name;
    private final Node value;

    public Declare(Type type, String name, Node value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public Node build() {
        return new CDeclareNode(type, name, value);
    }
}
