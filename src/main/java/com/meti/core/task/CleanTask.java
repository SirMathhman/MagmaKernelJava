package com.meti.core.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CleanTask implements Task {
	private static final Path EXECUTABLE = Paths.get("a.exe");
	private static final Path OUT = Paths.get("out.c");
	private final Logger logger;

	public CleanTask(Logger logger) {
		this.logger = logger;
	}

	@Override
	public boolean canExecute(String line) {
		return "clean".equals(line);
	}

	@Override
	public void execute(String line) {
		try {
			Files.deleteIfExists(OUT);
			Files.deleteIfExists(EXECUTABLE);
		} catch (IOException e) {
			logger.log(Level.WARNING, "Failed to clean.", e);
		}
	}
}
