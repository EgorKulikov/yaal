package net.egork.utils.old.test;

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
		String result = (input + " ").replace('\n', ' ');
		if (result.length() > 30)
			result = result.substring(0, 30) + "...";
		return result;
	}
}
