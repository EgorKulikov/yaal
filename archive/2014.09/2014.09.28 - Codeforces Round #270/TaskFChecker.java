package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.InputMismatchException;


public class TaskFChecker implements Checker {
    public TaskFChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
		try {
			return check(in, expected, actual);
		} catch (InputMismatchException e) {
			return new Verdict(Verdict.VerdictType.PE, e.getMessage());
		}
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
		int steps = actual.readInt();
		boolean can = expected.readInt() != -1;
		if ((steps != -1) ^ can) {
			return Verdict.WA;
		}
		if (steps == -1) {
			return Verdict.OK;
		}
		int count = in.readInt();
		int[] x = IOUtils.readIntArray(in, count);
		int[] y = IOUtils.readIntArray(in, count);
		for (int i = 0; i < steps; i++) {
			int from = actual.readInt() - 1;
			int to = actual.readInt() - 1;
			x[from] ^= x[to];
		}
        return Arrays.equals(x, y) ? Verdict.OK : Verdict.WA;
    }
}
