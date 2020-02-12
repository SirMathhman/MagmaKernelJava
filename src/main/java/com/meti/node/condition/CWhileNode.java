package com.meti.node.condition;

import com.meti.node.Node;

public class CWhileNode implements Node {
    private final Node condition;
    private final Node block;

    public CWhileNode(Node condition, Node block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public String render() {
        return "while(" + condition.render() + ")" + block.render();
    }
}
