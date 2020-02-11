package com.meti.node.condition;

import com.meti.node.Node;

public class CElseNode implements Node {
    private final Node block;

    CElseNode(Node block) {
        this.block = block;
    }

    @Override
    public String render() {
        return "else" + block.render();
    }
}
