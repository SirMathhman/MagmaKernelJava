package com.meti.declare;

import com.meti.Compiler;
import com.meti.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DeclareParser implements Parser {
    private final Declarations declarations;

    public DeclareParser(Declarations declarations) {
        this.declarations = declarations;
    }

    @Override
    public Optional<Node> parse(String value, Compiler compiler) {
        String trim = value.trim();
        if (trim.contains("=")) {
            int equals = trim.indexOf('=');
            String first = trim.substring(0, equals).trim();
            String last = trim.substring(equals + 1).trim();
            int lastSpace = first.lastIndexOf(' ');
            String flagString = first.substring(0, lastSpace);
            List<Flag> flags = Arrays.stream(flagString.split(" "))
                    .map(String::toUpperCase)
                    .map(Flag::valueOf)
                    .collect(Collectors.toList());
            String name = first.substring(lastSpace + 1);
            if (flags.contains(Flag.VAL) || flags.contains(Flag.VAR)) {
                Type type = compiler.resolveValue(last);
                if (flags.contains(Flag.NATIVE)) {
                    declarations.define(name, type);
                    return Optional.of(new EmptyNode());
                } else {
                    Node valueNode = declarations.define(name, type, () -> compiler.parse(last));

                    return Optional.of(new DeclareNode(type, name, valueNode));
                }
            }
        }
        return Optional.empty();
    }
}