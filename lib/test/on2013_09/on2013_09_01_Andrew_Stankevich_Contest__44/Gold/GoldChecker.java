package on2013_09.on2013_09_01_Andrew_Stankevich_Contest__44.Gold;



import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class GoldChecker implements Checker {
    public GoldChecker(String parameters) {
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
		int answer = expected.readInt();
		int actualAnswer = actual.readInt();
		if (answer > actualAnswer)
			return Verdict.WA;
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		int[] color = new int[count];
		IOUtils.readIntArrays(in, x, y, color);
		int realAnswer = 0;
		int[] x0 = new int[3];
		int[] y0 = new int[3];
		int[] x1 = new int[3];
		int[] y1 = new int[3];
		IOUtils.readIntArrays(actual, x0, y0, x1, y1);
		for (int i = 0; i < 3; i++) {
			if (x0[i] >= x1[i] || y0[i] >= y1[i])
				return Verdict.WA;
			for (int j = 0; j < i; j++) {
				if (Math.max(x0[i], x0[j]) < Math.min(x1[i], x1[j]) && Math.max(y0[i], y0[j]) < Math.min(y1[i], y1[j]))
					return Verdict.WA;
			}
			for (int j = 0; j < count; j++) {
				if (x[j] >= x0[i] && x[j] <= x1[i] && y[j] >= y0[i] && y[j] <= y1[i] && color[j] == i)
					realAnswer++;
			}
		}
		if (realAnswer != actualAnswer)
			return Verdict.WA;
		if (actualAnswer > answer)
			throw new IllegalStateException();
        return Verdict.OK;
    }
}
