package com.meti;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StructNodeTest {
	@Test
	void render() {
		Cache cache = new ListCache();
		Type type = new MappedStructType(PrimitiveType.INT);
		Node struct = new StructNode("main", type, new BlockNode(new ReturnNode(new IntNode(0))));
		Node declare = new DeclareNode("main", type, struct);
		String result = declare.render(cache);
		assertEquals("int main_(){return 0;}" +
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
		assertEquals("struct main{}" +
		             "int lambda_(){return 10;}" +
		             "int (*main_())(){struct main main={};return lambda_;}" +
		             "int (*(*main)())()=main_;", cache.render() + result);
	}


	@Test
	void renderClass() {
		Cache cache = new ListCache();
		Type type = new MappedStructType(new NativeStructType("Point"));
		Type lengthType = new MappedStructType(PrimitiveType.INT);
		Node lengthNode = new StructNode(lengthType,
				new BlockNode(new ReturnNode(new IntNode(10))), "Point", "length");
		Node length = new DeclareNode("length", lengthType, lengthNode);
		Node returnNode = new ReturnNode(new VariableNode("Point"));
		Node struct = new StructNode("Point", type, new BlockNode(List.of(length, returnNode)));
		Node declare = new DeclareNode("Point", type, struct);
		String result = declare.render(cache);
		assertEquals("struct Point{" +
		             "int (*length)(void *);" +
		             "};" +
		             "int Point_length_(void *Point){return 10;}" +
		             "struct Point Point_(){" +
		             "struct Point Point={NULL};" +
		             "int (*length)(void *)=Point_length_;" +
		             "Point.length=length;" +
		             "return Point;}" +
		             "struct Point (*Point)()=Point_;", cache.render() + result);
	}
}