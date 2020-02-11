package com.meti.node.block;

import com.meti.node.Node;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class CContentNode implements Node {
    private final Collection<? extends Node> children;
    private final String delimiter;

    public CContentNode(Node node) {
        this(Collections.singleton(node));
    }

    public CContentNode(Collection<? extends Node> children, String delimiter) {
        this.children = children;
        this.delimiter = delimiter;
    }

    public CContentNode(Collection<? extends Node> children) {
        this(children, "");
    }

    @Override
    public String render() {
        return "{" + joinChildren() + "}";
    }

    private String joinChildren() {
        return children.stream()
                .map(Node::render)
                .collect(Collectors.joining(delimiter));
    }
}
