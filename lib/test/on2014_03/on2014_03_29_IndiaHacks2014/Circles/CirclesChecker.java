package on2014_03.on2014_03_29_IndiaHacks2014.Circles;



import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class CirclesChecker implements Checker {
	static double totalScore = 0;
	static int testCases = 0;

    public CirclesChecker(String parameters) {
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
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int cCount = actual.readInt();
		int[] cx = new int[cCount];
		int[] cy = new int[cCount];
		int[] r = new int[cCount];
		IOUtils.readIntArrays(actual, cx, cy, r);
		if (cCount > count)
			return Verdict.WA;
		for (int i = 0; i < cCount; i++) {
			if (cx[i] < 0 || cx[i] > 100 || cy[i] < 0 || cy[i] > 100 || r[i] < 1 || r[i] > 100)
				return Verdict.WA;
		}
		for (int i = 0; i < count; i++) {
			boolean found = false;
			for (int j = 0; j < count; j++) {
				int dx = x[i] - cx[j];
				int dy = y[i] - cy[j];
				if (dx * dx + dy * dy <= r[j] * r[j]) {
					found = true;
					break;
				}
			}
			if (!found)
				return new Verdict(Verdict.VerdictType.WA, "Point " + i + " not covered");
		}
		double score = (double)count / ArrayUtils.sumArray(r);
		totalScore += score;
		testCases++;
        return new Verdict(Verdict.VerdictType.OK, "Test score = " + score + ", total score = " + totalScore / testCases);
    }
}
