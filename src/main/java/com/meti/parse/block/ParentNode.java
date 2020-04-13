package com.meti.parse.block;

import com.meti.parse.Node;

import java.util.List;

public interface ParentNode extends Node {
	List<Node> children();
}
