package com.meti.node.transform.operate;

import com.meti.Cache;
import com.meti.Compiler;
import com.meti.core.task.MagmaCompiler;
import com.meti.node.Node;
import com.meti.parse.Declarations;
import com.meti.parse.TreeDeclarations;
import com.meti.util.CollectionCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvocationOperationTest {
	private Cache cache;
	private Compiler compiler;
	private Declarations declarations;

	@Test
	void order0() {
		Node node = compiler.parse("empty(1 - 3)");
		assertEquals("empty(1-3)", node.render());
	}

	@Test
	void order1() {
		Node node = compiler.parse("empty(1) - empty(3)");
		assertEquals("empty(1)-empty(3)", node.render());
	}

	@BeforeEach
	void setUp() {
		cache = new CollectionCache();
		declarations = new TreeDeclarations();
		compiler = new MagmaCompiler(cache, declarations);
		compiler.parse("val empty=(Int x)=>Int:{return x;}");
	}
}