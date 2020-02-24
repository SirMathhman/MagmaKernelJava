package com.meti.node.struct;

import com.meti.Cache;
import com.meti.Compiler;
import com.meti.core.task.MagmaCompiler;
import com.meti.parse.Declarations;
import com.meti.parse.TreeDeclarations;
import com.meti.util.CollectionCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectTest {
    @Test
    void test() {
        Declarations declarations = new TreeDeclarations();
        Cache cache = new CollectionCache();
        Compiler compiler = new MagmaCompiler(cache, declarations);
        compiler.parse("class val Some = [Int value] : {\n" +
                       "    val getValue ==> Int : {\n" +
                       "        return value;\n" +
                       "    };\n" +
                       "    val compare = [Some other] => Int :{\n" +
                       "        return value - (other.getValue());\n" +
                       "    };\n" +
                       "}\n");
        Assertions.assertEquals("int _exitCode=0;" +
                "void *_throw=NULL;" +
                "struct Some{int value;};" +
                "struct Some Some(int value);" +
                "int Some_getValue(void *_Some_){" +
                "struct Some Some_=*(struct Some*)(_Some_;)" +
                "return Some_.value;}" +
                "int Some_compare(struct Some other,void *_Some_){" +
                "struct Some Some_=*(struct Some*)(_Some_;)" +
                "return Some_.value-Some_getValue(other);}" +
                "struct Some Some(int value){" +
                "struct Some Some_={value};" +
                "return Some_;}" +
                "int main(){" +
                "return _exitCode;}", cache.render());
    }
}
