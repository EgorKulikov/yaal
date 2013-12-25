package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class DeletingNumbersChecker implements Checker {
	static double totalScore = 0;
	static int testCases = 0;

    public DeletingNumbersChecker(String parameters) {
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
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int size = count;
		int moveCount = actual.readInt();
		for (int i = 0; i < moveCount; i++) {
			int from = actual.readInt();
			int step = actual.readInt();
			if (from > size || step > size)
				return Verdict.WA;
			int value = array[--from];
			for (int j = from; j < size; j += step) {
				if (array[j] != value)
					return Verdict.WA;
			}
			int k = from;
			int next = from + step;
			for (int j = from + 1; j < size; j++) {
				if (j == next)
					next += step;
				else
					array[k++] = array[j];
			}
			size = k;
		}
		if (size != 0)
			return Verdict.WA;
		double currentScore = 100 * (double)moveCount / count;
		totalScore += currentScore;
		testCases++;
        return new Verdict(Verdict.VerdictType.OK, "Current score = " + currentScore + ", total score = " + totalScore / testCases);
    }
}
