package com.meti.interpret;

import com.meti.declare.Declarations;
import com.meti.node.Parser;
import com.meti.node.bracket.block.BlockParser;
import com.meti.node.bracket.declare.AssignParser;
import com.meti.node.bracket.declare.DeclareParser;
import com.meti.node.bracket.struct.Generator;
import com.meti.node.bracket.struct.ReturnParser;
import com.meti.node.bracket.struct.StructParser;
import com.meti.node.bracket.struct.ThisParser;
import com.meti.node.value.compound.invoke.InvocationParser;
import com.meti.node.value.compound.operator.OperationParser;
import com.meti.node.value.compound.variable.VariableParser;
import com.meti.node.value.primitive.array.*;
import com.meti.node.value.primitive.character.CharParser;
import com.meti.node.value.primitive.integer.IntParser;
import com.meti.node.value.primitive.point.DereferenceParser;
import com.meti.node.value.primitive.point.ReferenceParser;
import com.meti.node.value.primitive.string.StringParser;

import java.util.Arrays;
import java.util.Collection;

class MagmaParser extends ParentParser {
	MagmaParser(Declarations declarations, Generator generator, Cache cache) {
		this(
				new ThisParser(declarations),
				new ArrayDeleteParser(),
				new ArrayIndexParser(),
				new ReturnParser(),
				new BlockParser(),
				new ArrayContentParser(cache, generator),
				new ArraySizeParser(),
				new CharParser(),
				new StructParser(declarations, cache, generator),
				new DeclareParser(declarations),
				new AssignParser(),
				new IntParser(),
				new InvocationParser(declarations),
				new OperationParser(),
				new DereferenceParser(),
				new ReferenceParser(),
				new StringParser(),
				new VariableParser(declarations)
		);
	}

	private MagmaParser(Parser... children) {
		this(Arrays.asList(children));
	}

	private MagmaParser(Collection<Parser> children) {
		super(children);
	}
}
