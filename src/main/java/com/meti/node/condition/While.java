package com.meti.node.condition;

import com.meti.node.Node;
import com.meti.node.Statement;

public class While implements Statement {
    private final Node condition;
    private final Node block;

    public While(Node condition, Node block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public Node build() {
        return new CWhileNode(condition, block);
    }
}
