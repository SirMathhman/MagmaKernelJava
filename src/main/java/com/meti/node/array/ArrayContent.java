package com.meti.node.array;

import com.meti.node.CContent;
import com.meti.node.Node;
import com.meti.node.Statement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayContent implements Statement {
    private final List<? extends Node> elements;

    public ArrayContent(List<? extends Node> elements) {
        this.elements = elements;
    }

    @Override
    public Node build() {
        Collection<Node> children = buildChildren();
        return new CContent(children);
    }

    List<Node> buildChildren() {
        return elements.stream()
                .map(Statement.class::cast)
                .map(Statement::build)
                .collect(Collectors.toList());
    }
}
