package com.meti;

import com.meti.exception.ParseException;
import com.meti.primitive.CharParser;
import com.meti.primitive.Compiler;
import com.meti.primitive.IntParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompilerTest {
    private final Compiler compiler = new Compiler(new ParentParser(
            new IntParser(),
            new CharParser()
    ));

    @Test
    void compile() throws ParseException {
        assertEquals("'x'", compiler.parse("'x'").orElseThrow().render());
        assertEquals("10i", compiler.parse("10").orElseThrow().render());
    }
}
