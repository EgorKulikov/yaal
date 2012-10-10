package net.egork;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.StringInputStream;
import net.egork.chelper.tester.Verdict;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.util.Arrays;
import java.util.InputMismatchException;

public class NimChecker implements Checker {
	public NimChecker(String parameters) {
	}

	public Verdict check(String input, String expectedOutput, String actualOutput) {
		InputReader in = new InputReader(new StringInputStream(input));
		InputReader actual = new InputReader(new StringInputStream(actualOutput));
		int testCount = in.readInt();
		double points = 0;
		int notImproved = 0;
		for (int i = 0; i < testCount; i++) {
			try {
				int count = in.readInt();
				long[] numbers = IOUtils.readLongArray(in, count);
				long[] xors = new long[count];
				Arrays.fill(xors, -1);
				int[] answer = IOUtils.readIntArray(actual, count);
				for (int i1 = 0, answerLength = answer.length; i1 < answerLength; i1++) {
					int j = answer[i1];
					if (j < 1 || j > count)
						return new Verdict(Verdict.VerdictType.PE, "Test case " + i + " bad index");
					j--;
					if (xors[j] == -1)
						xors[j] = 0;
					xors[j] ^= numbers[i1];
				}
				int max = count;
				for (int j = count - 1; j >= 0; j--) {
					if (xors[j] == -1)
						max = j;
					else
						break;
				}
				for (int j = 0; j < max; j++) {
					if (xors[j] != 0)
						return new Verdict(Verdict.VerdictType.WA, "Test case " + i + " group #" + j + " is bad");
				}
				points += 100d * count / max;
				if (max == 1)
					notImproved++;
			} catch (InputMismatchException e) {
				return new Verdict(Verdict.VerdictType.PE, "Test case " + i + e.toString());
			}
		}
		return new Verdict(Verdict.VerdictType.OK, "Points: " + (points / testCount) + ", not improved: " + notImproved);
	}
}
