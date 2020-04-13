package com.meti.parse.block;

import com.meti.parse.Node;

import java.util.List;

interface ParentNode extends Node {
	List<Node> children();
}
