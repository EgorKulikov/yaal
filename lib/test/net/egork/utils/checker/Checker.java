package net.egork.utils.checker;

import net.egork.utils.io.old.InputReader;
import net.egork.utils.test.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Checker {
	public abstract String check(InputReader input, net.egork.utils.io.old.InputReader expectedOutput, InputReader actualOutput);
	public abstract double getCertainty();

	public Collection<Test> generateTests() {
		return Collections.<Test>emptyList();
	}

	public String tokenCheck(
		net.egork.utils.io.old.InputReader expectedOutput, net.egork.utils.io.old.InputReader actualOutput) {
		int index = 0;
		boolean readingResult = false;
		try {
			while (true) {
				readingResult = true;
				String resultToken = actualOutput.readString();
				readingResult = false;
				String outputToken = expectedOutput.readString();
				if (!resultToken.equals(outputToken)) {
					try {
						if (isDoubleEquals(Double.parseDouble(outputToken), Double.parseDouble(resultToken),
							getCertainty()))
						{
							index++;
							continue;
						}
					} catch (NumberFormatException ignored) {}
					return "'" + outputToken + "' expected at " + index + " but '" + resultToken + "' received";
				}
				index++;
			}
		} catch (InputMismatchException e) {
			if (readingResult) {
				try {
					expectedOutput.readString();
					return "only " + index + " tokens received";
				} catch (InputMismatchException e1) {
					return null;
				}
			} else
				return "only " + index + " tokens expected";
		}
	}

	public String strictCheck(InputReader expectedOutput, net.egork.utils.io.old.InputReader actualOutput) {
		while (true) {
			int ch1 = expectedOutput.read();
			while (ch1 == '\r')
				ch1 = expectedOutput.read();
			int ch2 = actualOutput.read();
			while (ch2 == '\r')
				ch2 = actualOutput.read();
			if (ch1 != ch2)
				return "WA";
			if (ch1 == -1)
				return null;
		}		
	}

	protected static boolean isDoubleEquals(double expected, double actual, double certainty) {
		return Math.abs(expected - actual) < certainty * Math.max(Math.abs(expected), 1);
	}
}
