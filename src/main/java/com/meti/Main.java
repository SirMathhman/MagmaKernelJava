package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static final Cache CACHE = new ListCache();
	public static final Compiler COMPILER = new MagmaCompiler();

	public static void main(String[] args) {
		try {
			Path main = Paths.get("main.magma");
			if (!Files.exists(main)) Files.createFile(main);
			String collect = String.join("", Files.readAllLines(main));
			String toParser = "val main : () => Int = {" + collect + "}";
			Node node = COMPILER.parse(toParser);
			String result = node.render(CACHE);
			Path mainOut = Paths.get("main.c");
			if (!Files.exists(mainOut)) Files.createFile(mainOut);
			Files.writeString(mainOut, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
