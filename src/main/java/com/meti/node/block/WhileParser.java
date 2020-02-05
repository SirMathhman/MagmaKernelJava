package com.meti.node.block;

import com.meti.Compiler;
import com.meti.Parser;
import com.meti.exception.ParseException;
import com.meti.node.Node;

import java.util.Optional;

public class WhileParser implements Parser {
    @Override
    public Optional<Node> parse(String content, Compiler compiler) {
        String trim = content.trim();
        if (trim.startsWith("while(")) {
            int index = -1;
            int depth = 0;
            String withoutHeader = trim.substring(6);
            char[] charArray = withoutHeader.toCharArray();
            int length = charArray.length;
            for (int i = 0; i < length; i++) {
                char c = charArray[i];
                if (c == ')' && depth == 0) {
                    index = i;
                    break;
                } else {
                    if (c == '(') depth++;
                    if (c == ')') depth--;
                }
            }
            if (index == -1) {
                throw new ParseException("Could not resolve condition of:\n\t" + trim);
            }
            String conditionString = withoutHeader.substring(0, index);
            String blockString = withoutHeader.substring(index + 1, withoutHeader.length() - 1);
            Node condition = compiler.parse(conditionString);
            Node block = compiler.parse(blockString);
            return Optional.of(new WhileNode(condition, block));
        }
        return Optional.empty();
    }
}