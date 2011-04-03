package net.egork.utils.checker;

import net.egork.utils.io.inputreader.InputReader;

import java.util.InputMismatchException;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public abstract class Checker {
	public abstract String check(InputReader input, InputReader expectedOutput, InputReader actualOutput);
	public abstract double getCertainty();

	public String tokenCheck(InputReader expectedOutput, InputReader actualOutput) {
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

	protected static boolean isDoubleEquals(double expected, double actual, double certainty) {
		return Math.abs(expected - actual) < certainty * Math.max(Math.abs(expected), 1);
	}
}
