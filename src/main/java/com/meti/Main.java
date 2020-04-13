package com.meti;

import com.meti.data.Cache;
import com.meti.data.ListCache;
import com.meti.parse.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Main {
	private static final Path MAIN = Paths.get("main.magma");
	private static final Path OUT = Paths.get("main.c");
	private static final Cache CACHE = new ListCache();
	private static final Compiler COMPILER = new MagmaCompiler();

	private Main() {
	}

	public static void main(String[] args) {
		try {
			String main = readInput();
			String result = process(main);
			writeOutput(result);
		} catch (Exception e) {
			handleException(e);
		}
	}

	private static String readInput() throws IOException {
		Path input = ensure(MAIN);
		return String.join("", Files.readAllLines(input));
	}

	private static String process(String main) {
		String mainEncapsulated = "val main : () => Int = {" + main + "}";
		Node node = COMPILER.parse(mainEncapsulated);
		return node.render(CACHE);
	}

	private static void writeOutput(String result) throws IOException {
		Path output = ensure(OUT);
		Files.writeString(output, CACHE.render() + result);
	}

	private static void handleException(Exception e) {
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

	private static Path ensure(Path path) throws IOException {
		if (!Files.exists(path)) Files.createFile(path);
		return path;
	}
}
