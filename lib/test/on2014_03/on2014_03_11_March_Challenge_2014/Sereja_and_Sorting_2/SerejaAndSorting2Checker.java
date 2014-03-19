package on2014_03.on2014_03_11_March_Challenge_2014.Sereja_and_Sorting_2;



import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class SerejaAndSorting2Checker implements Checker {
	static double totalScore = 0;
	static int testCount = 0;

    public SerejaAndSorting2Checker(String parameters) {
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
		int changes = actual.readInt();
		if (changes > count)
			return new Verdict(Verdict.VerdictType.WA, "Too many changes");
		int sum = 0;
		for (int i = 0; i < changes; i++) {
			int from = actual.readInt();
			int to = actual.readInt();
			sum += to - from + 1;
			if (from >= to || to > count || from < 1)
				return new Verdict(Verdict.VerdictType.PE, "Corrupt segment " + from + " " + to);
			for (int j = from - 1, k = to - 1; j < k; j++, k--) {
				int t = array[j];
				array[j] = array[k];
				array[k] = t;
			}
		}
		for (int i = 1; i < count; i++) {
			if (array[i] < array[i - 1])
				return new Verdict(Verdict.VerdictType.WA, "Not sorted");
		}
		double currentScore = changes + (double)sum / count;
		totalScore += currentScore;
		testCount++;
		return new Verdict(Verdict.VerdictType.OK, "Current score = " + currentScore * 10 + ", total score = " + totalScore * 10 / testCount);
    }
}
