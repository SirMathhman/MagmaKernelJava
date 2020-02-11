package com.meti.node.condition;

import com.meti.node.Node;
import com.meti.node.Statement;

public class If implements Statement {
    private final Node condition;
    private final Node block;

    public If(Node condition, Node block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public Node build() {
        return new CIfNode(condition, block);
    }
}
