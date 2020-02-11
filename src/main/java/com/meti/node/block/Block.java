package com.meti.node.block;

import com.meti.node.CContent;
import com.meti.node.Node;
import com.meti.node.Statement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Block implements Statement {
    private final Collection<Node> children;

    public Block(Collection<Node> children) {
        this.children = children;
    }

    @Override
    public Node build() {
        List<Node> nodes = buildChildren();
        return new CContent(nodes);
    }

    List<Node> buildChildren() {
        return children.stream()
                .map(Statement.class::cast)
                .map(Statement::build)
                .collect(Collectors.toList());
    }
}
