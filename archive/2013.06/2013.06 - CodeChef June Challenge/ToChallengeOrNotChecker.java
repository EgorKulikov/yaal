package net.egork;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.collections.sequence.Array;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.numbers.FastFourierTransform;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;
import java.util.Set;


public class ToChallengeOrNotChecker implements Checker {
	static double totalScore = 0;
	static int testCount = 0;

    public ToChallengeOrNotChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
        return check(in, expected, actual);
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
		try {
			testCount++;
			Set<Integer> initial = new EHashSet<Integer>(Array.wrap(IOUtils.readIntArray(in, in.readInt())));
			int count = actual.readInt();
			Set<Integer> solution = new EHashSet<Integer>(Array.wrap(IOUtils.readIntArray(actual, count)));
			if (!initial.containsAll(solution)) {
				return new Verdict(Verdict.VerdictType.WA, "Unknown number");
			}
			if (solution.size() != count) {
				return new Verdict(Verdict.VerdictType.WA, "Duplicate");
			}
			long[] x = new long[100000];
			for (int i : solution)
				x[i]++;
			long[] result = FastFourierTransform.multiply(x, x);
			for (int i : solution) {
				if (result[2 * i] > 1)
					return new Verdict(Verdict.VerdictType.WA, "Progression found");
			}
			double currentScore = 100d * count / initial.size();
			totalScore += currentScore;
			return new Verdict(Verdict.VerdictType.OK, "Test score = " + currentScore + ", total score = " + (totalScore / testCount));
		} catch (InputMismatchException e) {
			return new Verdict(Verdict.VerdictType.PE, e.getMessage() == null ? "" : e.getMessage());
		}
    }
}
