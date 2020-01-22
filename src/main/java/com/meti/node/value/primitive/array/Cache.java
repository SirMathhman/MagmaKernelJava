package com.meti.node.value.primitive.array;

import com.meti.node.Node;
import com.meti.node.bracket.struct.Generator;
import com.meti.node.bracket.struct.FunctionNodeBuilder;

import java.util.stream.Stream;

public interface Cache {
    void add(FunctionNodeBuilder builder);

    void add(Node node);

    Stream<Node> stream();
}
