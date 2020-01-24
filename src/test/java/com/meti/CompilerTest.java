package com.meti;

import com.meti.exception.ParseException;
import com.meti.primitive.CharParser;
import com.meti.primitive.IntParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompilerTest {
    private final Compiler compiler = new Compiler(new ParentParser(
            new IntParser(),
            new CharParser()
    ), null);

    @Test
    void compile() throws ParseException {
        assertEquals("'x'", compiler.parse("'x'").render());
        assertEquals("10i", compiler.parse("10").render());
    }
}