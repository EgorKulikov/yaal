package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;

import static net.egork.misc.ArrayUtils.sumArray;


public class ArrayConstructionChecker implements Checker {
    public static final int TESTS_PER_TYPE = 33;
    private static long type1 = 0;
    private static int qType1 = 0;
    private static long type2 = 0;
    private static int qType2 = 0;
    private static long type3 = 0;
    private static int qType3 = 0;
    private static int testNumber = 0;

    public ArrayConstructionChecker(String parameters) {
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
        int[][] c = in.readIntTable(n, n);
        int[] a = actual.readIntArray(n);
        if (!actual.isExhausted()) {
            return new Verdict(Verdict.VerdictType.PE, "More than n numbers");
        }
        if (sumArray(a) != m) {
            return new Verdict(Verdict.VerdictType.PE, "Sum is incorrect");
        }
        for (int i = 0; i < n; i++) {
            if (a[i] < 0) {
                return new Verdict(Verdict.VerdictType.PE, "Negative number");
            }
        }
        long score = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                score += ((long)a[i] - a[j]) * (a[i] - a[j]) * c[i][j];
            }
        }
        if (testNumber < TESTS_PER_TYPE) {
            qType1++;
            type1 += score;
        } else if (testNumber < 2 * TESTS_PER_TYPE) {
            qType2++;
            type2 += score;
        } else {
            qType3++;
            type3 += score;
        }
        testNumber++;
        return new Verdict(Verdict.VerdictType.OK, String.format("Current score = %d, type1 average = %.2f, type2 " +
                "average = %.2f, type3 average = %.2f, total average = %.2f", score, (double)type1 / qType1 / 1000000,
                (double)type2 / qType2 / 1000000, (double)type3 / qType3 / 1000000, (double)(type1 + type2 + type3) / (qType1 + qType2 + qType3
                ) / 1000000));
    }
}
