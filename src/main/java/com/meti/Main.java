package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static final Compiler compiler = new MagmaCompiler();
	private static final Path BUILD = Paths.get("build.magma");

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try {
			if (!Files.exists(BUILD)) {
				Files.createFile(BUILD);
			}
			List<String> lines = Files.readAllLines(BUILD);
			parse(String.join("", lines));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parse(String content) {
		String formmated = "val main : () => Int; main = {" + content + "}";
		compiler.parse(formmated);
	}
}
