package com.meti.core.task;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CleanTaskTest {
	private static final Path EXECUTABLE = Paths.get("a.exe");
	private static final Path OUT = Paths.get("out.c");

	@Test
	void execute() throws IOException {
		if (!exists(OUT)) createFile(OUT);
		if (!exists(EXECUTABLE)) createFile(EXECUTABLE);

		Task task = new CleanTask(Logger.getAnonymousLogger());
		task.execute("clean");

		assertFalse(deleteIfExists(OUT));
		assertFalse(deleteIfExists(EXECUTABLE));
	}

	@Test
	void testCanExecute() {
		Task task = new CleanTask(Logger.getAnonymousLogger());
		assertTrue(task.canExecute("clean"));
	}
}