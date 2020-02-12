package com.meti.node.primitive.bool;

import com.meti.node.Node;
import com.meti.node.primitive.ints.CIntNode;

public class BooleanNode implements Node {
    public static final Node FALSE = new CIntNode(0);
    public static final Node TRUE = new CIntNode(1);
    private final boolean isTrue;

    public BooleanNode(boolean isTrue) {
        this.isTrue = isTrue;
    }

    @Override
    public String render() {
        return (isTrue) ?
                TRUE.render() :
                FALSE.render();
    }
}
