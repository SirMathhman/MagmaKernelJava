package com.meti;

import com.meti.data.Cache;
import com.meti.data.ListCache;
import com.meti.parse.Node;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Main {
	public static final Cache CACHE = new ListCache();
	public static final Compiler COMPILER = new MagmaCompiler();

	private Main() {
	}

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
			Files.writeString(mainOut, CACHE.render() + result);
		} catch (Exception e) {
			Throwable t = e;
			StringBuilder builder = new StringBuilder();
			do {
				String message = t.getMessage();
				builder.append(message)
						.append(" ")
						.append(t.getStackTrace()[0])
						.append("\n");
				t = t.getCause();
			} while (null != t);
			System.out.println(builder);
		}
	}
}
