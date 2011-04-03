package net.egork.utils.test;

import java.io.Serializable;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Test implements Serializable {
	private static final long serialVersionUID = 42L;

	private final String input;
	private final String expectedOutput;

	public Test(String input, String expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	public String getInput() {
		return input;
	}

	public String getExpectedOutput() {
		return expectedOutput;
	}

	@Override
	public String toString() {
		return input + "\n";
	}
}
