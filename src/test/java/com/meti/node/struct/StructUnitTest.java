package com.meti.node.struct;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

class StructUnitTest {
    private final Cache cache = new CollectionCache();
    private Compiler compiler = null;

    @Test
    void innerDeclaraion() {
        compiler.parse("val empty =: {\n" +
                "	val x = 10;\n" +
                "	val y = x;\n" +
                "}\n");
        assertEquals("int _exitCode=0;" +
                "void *_throw=NULL;" +
                "void empty(){" +
                "int x=10;" +
                "int y=x;}" +
                "int main(){" +
                "return _exitCode;}", cache.render());
    }

    @Test
    void empty() {
        compiler.parse("val empty =: {}");
        assertEquals("int _exitCode=0;void *_throw=NULL;void empty(){}int main(){return _exitCode;}", cache.render());
    }

    @Test
    void parseComplete() {
        Node node = compiler.parse("val complete = [Int value] => Int : {return value;}");
        assertTrue(node.render().isBlank());
        assertEquals("int _exitCode=0;void *_throw=NULL;int complete(int value){return value;}int main(){return _exitCode;}",
                cache.render());
    }

    @BeforeEach
    void setUp() {
        Declarations declarations = new TreeDeclarations();
        compiler = new MagmaCompiler(cache, declarations);
    }

    @Test
    void withParam() {
        compiler.parse("val accept = [Int some] : {}");
        assertEquals("int _exitCode=0;void *_throw=NULL;void accept(int some){}int main(){return _exitCode;}",
                cache.render());
    }

    @Test
    void withTwoParam() {
        compiler.parse("val accept = [Int one, Int two] : {}");
        assertEquals("int _exitCode=0;void *_throw=NULL;void accept(int one,int two){}int main(){return _exitCode;}",
                cache.render());
    }
}