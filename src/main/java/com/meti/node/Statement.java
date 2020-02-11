package com.meti.node;

public interface Statement extends Node {
    Node build();

    default String render() {
        return build().render();
    }
}
