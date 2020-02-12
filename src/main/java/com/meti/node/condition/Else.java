package com.meti.node.condition;

import com.meti.node.Node;
import com.meti.node.Statement;

public class Else implements Statement {
    private final Node node;

    public Else(Node node) {
        this.node = node;
    }

    @Override
    public Node build() {
        return new CElseNode(node);
    }
}
