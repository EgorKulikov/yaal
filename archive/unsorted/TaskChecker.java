package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.chelper.task.Test;

import java.util.Collection;
import java.util.Collections;

public class TaskChecker {
	public String check(InputReader input, InputReader expected, InputReader actual) {
		return "";
	}

	public double getCertainty() {
		return 0;
	}

	public Collection<? extends Test> generateTests() {
		return Collections.emptyList();
	}
}
