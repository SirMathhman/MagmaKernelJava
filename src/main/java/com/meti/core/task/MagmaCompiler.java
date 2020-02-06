package com.meti.core.task;

import com.meti.Cache;
import com.meti.Parser;
import com.meti.Resolver;
import com.meti.Unit;
import com.meti.node.block.BlockParser;
import com.meti.node.block.BlockResolver;
import com.meti.node.condition.ElseParser;
import com.meti.node.condition.IfParser;
import com.meti.node.condition.WhileParser;
import com.meti.node.declare.*;
import com.meti.node.point.AnyResolver;
import com.meti.node.point.DereferenceParser;
import com.meti.node.point.PointerResolver;
import com.meti.node.primitive.*;
import com.meti.node.struct.*;
import com.meti.node.thrower.CatchParser;
import com.meti.node.thrower.ThrowParser;
import com.meti.node.thrower.TryParser;
import com.meti.node.transform.*;
import com.meti.util.ParentParser;
import com.meti.util.ParentResolver;
import com.meti.util.UnitCompiler;

class MagmaCompiler extends UnitCompiler {
    MagmaCompiler(Cache cache) {
        this(cache, new TreeDeclarations());
    }

    private MagmaCompiler(Cache cache, Declarations declarations) {
        this(declarations, new StructUnit(declarations, cache), cache);
    }

    private MagmaCompiler(Declarations declarations, Unit unit, Cache cache) {
        this(new ParentParser(
                        unit,
                        new DoubleParser(),
                        new CharParser(),
                        new WhileParser(),
                        new DereferenceParser(),
                        new IfParser(),
                        new CastParser(),
                        new BlockParser(),
                        new TryParser(),
                        new CatchParser(declarations),
                        new ThrowParser(declarations, cache),
                        new NullParser(),
                        new ReturnParser(),
                        new DeclareParser(declarations),
                        new NotParser(),
                        new ElseParser(),
                        new BooleanParser(),
                        new InvocationParser(declarations),
                        new OperationParser(),
                        new ThisParser(declarations),
                        new IntParser(),
                        new StringParser(),
                        new VariableParser(declarations)
                ),
                new ParentResolver(
                        unit,
                        new CastResolver(),
                        new CharResolver(),
                        new OperationResolver(),
                        new BlockResolver(declarations),
                        new VoidResolver(),
                        new BooleanResolver(),
                        new IntResolver(),
                        new FloatResolver(),
                        new DoubleResolver(),
                        new AnyResolver(),
                        new PointerResolver(),
                        new InvocationResolver(declarations),
                        new StringResolver(),
                        new FloatResolver(),
                        new VariableResolver(declarations),
                        new ObjectResolver(declarations)
                ));
    }

    private MagmaCompiler(Parser rootParser, Resolver rootResolver) {
        super(rootParser, rootResolver);
    }
}
