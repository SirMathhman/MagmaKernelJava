package com.meti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StructNodeTest {
	@Test
	void render() {
		Cache cache = new ListCache();
		Type type = new MappedStructType(PrimitiveType.INT);
		Node struct = new StructNode("main", type, new BlockNode(new ReturnNode(new IntNode(0))));
		Node declare = new DeclareNode("main", type, struct);
		String result = declare.render(cache);
		Assertions.assertEquals("int main_(){return 0;}" +
		                        "int (*main)()=main_;", cache.render() + result);
	}

	@Test
	void renderChild() {
		Cache cache = new ListCache();
		Type subType = new MappedStructType(PrimitiveType.INT);
		Type type = new MappedStructType(subType);
		Node lambdaNode = new StructNode("lambda", subType, new BlockNode(new ReturnNode(new IntNode(10))));
		Node struct = new StructNode("main", type, new BlockNode(new ReturnNode(lambdaNode)));
		Node declare = new DeclareNode("main", type, struct);
		String result = declare.render(cache);
		Assertions.assertEquals("struct main{}" +
		                        "int lambda_(){return 10;}" +
		                        "int (*main_())(){return lambda_;}" +
		                        "int (*(*main)())()=main_", cache.render() + result);
	}
}