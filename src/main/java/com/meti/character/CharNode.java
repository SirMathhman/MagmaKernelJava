package com.meti.character;

import com.meti.Node;

import java.util.LinkedList;

public class CharNode implements Node {
	private final char value;

	public CharNode(char value) {
		this.value = value;
	}

	@Override
	public LinkedList<Node> children() {
		return new LinkedList<>();
	}

	@Override
	public String render() {
		return String.valueOf(value);
	}

    @Override
    public boolean isParent() {
		return false;
    }
}
