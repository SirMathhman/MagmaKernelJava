package com.meti.node.declare;

import com.meti.node.Node;
import com.meti.node.Type;

public class CDeclareNode implements Node {
    private final String name;
    private final Type type;
    private final Node value;

    public CDeclareNode(Type type, String name, Node value) {
        this.value = value;
        this.type = type;
        this.name = name;
    }

    @Override
    public String render() {
        String renderedValue = value.render();
        return renderedValue.isBlank() ? "" :
                type.render(name) + "=" + renderedValue + ";";
    }
}