package on2016_08.on2016_08_26_August_Circuits.Petro_and_Cycles__Approximate_problem_;



import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.generated.collections.set.IntHashSet;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;

import static net.egork.io.IOUtils.readIntArrays;
import static net.egork.misc.MiscUtils.decreaseByOne;


public class CyclesChecker implements Checker {
    static double totalScore;
    static int numTests;

    public CyclesChecker(String parameters) {
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
        int n = in.readInt();
        int m = in.readInt();
        int[] x = new int[m];
        int[] y = new int[m];
        readIntArrays(in, x, y);
        decreaseByOne(x, y);
        boolean[] used = new boolean[m];
        int q = actual.readInt();
        for (int i = 0; i < q; i++) {
            int a = actual.readInt() - 1;
            int b = actual.readInt() - 1;
            int c = actual.readInt() - 1;
            if (used[a] || used[b] || used[c] || a == b || b == c || c == a) {
                return Verdict.WA;
            }
            used[a] = used[b] = used[c] = true;
            IntHashSet set = new IntHashSet();
            set.add(x[a]);
            set.add(y[a]);
            set.add(x[b]);
            set.add(y[b]);
            set.add(x[c]);
            set.add(y[c]);
            if (set.size() != 3) {
                return Verdict.WA;
            }
        }
        long testScore = (long)(1e9 * q / n);
        totalScore += testScore;
        numTests++;
        return new Verdict(Verdict.VerdictType.OK, String.format("Test score = %d, average score = %.2f", testScore,
                totalScore / numTests));
    }
}
