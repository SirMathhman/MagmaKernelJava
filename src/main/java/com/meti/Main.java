package com.meti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static final Cache CACHE = new Cache();
	public static final Path META = Paths.get("meta");
	private static final Path BUILD = Paths.get("build.magma");
	public static final Path BUILD_OUTPUT = META.resolve("build.c");
	public static final Compiler compiler = new MagmaCompiler(CACHE);

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try {
			if (!Files.exists(BUILD)) {
				Files.createFile(BUILD);
			}
			List<String> lines = Files.readAllLines(BUILD);
			String output = parse(String.join("", lines));
			if (!Files.exists(META)) Files.createDirectory(META);
			if (!Files.exists(BUILD_OUTPUT)) Files.createFile(BUILD_OUTPUT);
			Files.writeString(BUILD_OUTPUT, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String parse(String content) {
		String pre = compiler.parse("val main : () => Int = {" + content + "}").renderJoined();
		return CACHE.render() + pre;
	}
}
