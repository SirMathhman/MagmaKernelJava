package com.meti;

import com.meti.data.Cache;
import com.meti.data.MappedCache;
import com.meti.parse.Node;
import com.meti.parse.Type;
import com.meti.parse.block.*;
import com.meti.parse.store.DeclareNode;
import com.meti.parse.store.VariableNode;
import com.meti.primitive.IntNode;
import com.meti.primitive.PrimitiveType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleStructNodeTest {
	@Test
	void render() {
		Cache cache = new MappedCache();
		Type type = new MappedStructType(PrimitiveType.INT);
		Node struct = new SimpleStructNode(type, new BlockNode(new ReturnNode(new IntNode(0))), "main");
		Node declare = new DeclareNode("main", type, struct);
		String result = declare.render(cache);
		assertEquals("int main_(){return 0;}" +
		             "int (*main)()=main_;", cache.render() + result);
	}

	@Test
	void renderChild() {
		Cache cache = new MappedCache();
		Type subType = new MappedStructType(PrimitiveType.INT);
		Type type = new MappedStructType(subType);
		Node lambdaNode = new SimpleStructNode(subType, new BlockNode(new ReturnNode(new IntNode(10))), "lambda");
		Node struct = new SimpleStructNode(type, new BlockNode(new ReturnNode(lambdaNode)), "main");
		Node declare = new DeclareNode("main", type, struct);
		String result = declare.render(cache);
		assertEquals("struct main{int (*lambda)(void *);};" +
		             "int lambda_(void *main){return 10;}" +
		             "int (*main_())(void *){struct main main={NULL};return lambda_;}" +
		             "int (*(*main)())()=main_;", cache.render() + result);
	}


	@Test
	void renderClass() {
		Cache cache = new MappedCache();
		Type type = new MappedStructType(new NativeStructType("Point"));
		Type lengthType = new MappedStructType(PrimitiveType.INT);
		Node lengthNode = new SimpleStructNode(lengthType,
				new BlockNode(new ReturnNode(new IntNode(10))), "Point", "length");
		Node length = new DeclareNode("length", lengthType, lengthNode);
		Node returnNode = new ReturnNode(new VariableNode("Point"));
		Node struct = new SimpleStructNode(type, new BlockNode(List.of(length, returnNode)), "Point");
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