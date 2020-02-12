package com.meti.node.primitive.ints;

import com.meti.node.Node;

public class CIntNode implements Node {
    private final int value;

    public CIntNode(int value) {
        this.value = value;
    }

    @Override
    public String render() {
        return value + "";
    }
}
