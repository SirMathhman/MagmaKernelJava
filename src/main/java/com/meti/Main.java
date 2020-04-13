package com.meti;

import com.meti.data.Cache;
import com.meti.data.ListCache;
import com.meti.parse.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Main {
	private static final int LOGGING_TRUNCATE = 100;
	private static final Cache CACHE = new ListCache();
	private static final Compiler COMPILER = new MagmaCompiler();
	private static final Path MAIN = Paths.get("main.magma");
	private static final Path OUT = Paths.get("main.c");
	private static final Logger logger = Logger.getAnonymousLogger();

	private Main() {
	}

	public static void main(String[] args) {
		logger.log(Level.INFO, "Starting compiler.");
		try {
			String main = readInput();
			String result = process(main);
			writeOutput(result);
		} catch (Exception e) {
			handleException(e);
		}
		logger.log(Level.INFO, "Exiting compiler.");
	}

	private static String readInput() throws IOException {
		Path input = ensure(MAIN);
		List<String> lines = Files.readAllLines(input);
		logger.log(Level.INFO, "Read in " + lines.size() + " lines of input.");
		return String.join("", lines);
	}

	private static String process(String main) {
		String mainEncapsulated = wrapInMain(main);
		Node node = COMPILER.parse(mainEncapsulated);
		return node.render(CACHE);
	}

	private static void writeOutput(String result) throws IOException {
		Path output = ensure(OUT);
		String renderedCache = CACHE.render();
		String value = renderedCache + result;
		Files.writeString(output, value);
		logger.log(Level.INFO, "Writing output with length " + value.length() + ".");
	}

	private static void handleException(Exception e) {
		String value = convertStackToString(e);
		logger.log(Level.SEVERE, "An error occurred:\n" + value);
	}

	private static Path ensure(Path path) throws IOException {
		if (!Files.exists(path)) {
			logger.log(Level.WARNING, path.toAbsolutePath() + " does not exist. It will be created.");
			Files.createFile(path);
		}
		return path;
	}

	private static String wrapInMain(String main) {
		return new StringBuilder()
				.append("val main : () => Int = {")
				.append(main)
				.append("}")
				.toString();
	}

	private static String convertStackToString(Exception e) {
		Throwable current = e;
		StringBuilder builder = new StringBuilder();
		do {
			builder = format(current, builder);
			current = current.getCause();
		} while (null != current);
		return builder.toString();
	}

	private static StringBuilder format(Throwable current, StringBuilder builder) {
		String message = current.getMessage();
		int length = message.length();
		int size = Math.min(length, LOGGING_TRUNCATE);
		String padding = fitPadding(message);
		message = message.substring(0, size) + padding;
		return builder.append(message)
				.append(" ")
				.append(current.getStackTrace()[0])
				.append("\n");
	}

	private static String fitPadding(CharSequence message) {
		return IntStream.range(0, LOGGING_TRUNCATE - message.length())
				.mapToObj(value -> " ")
				.collect(Collectors.joining(""));
	}
}
