package net.egork;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;


public class TaskDChecker implements Checker {
    public TaskDChecker(String parameters) {
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
        int d1 = in.readInt();
        int d2 = in.readInt();
        int[] x = new int[n * n];
        int[] y = new int[n * n];
        actual.readIntArrays(x, y);
        List<IntIntPair> bad = new ArrayList<>();
        int j = 1000;
        for (int i = 0; i * i <= d1; i++) {
            while (i * i + j * j > d1) {
                j--;
            }
            if (i * i + j * j == d1) {
                bad.add(new IntIntPair(i, j));
                if (i != 0) {
                    bad.add(new IntIntPair(-i, j));
                    if (j != 0) {
                        bad.add(new IntIntPair(-i, -j));
                    }
                }
                if (j != 0) {
                    bad.add(new IntIntPair(i, -j));
                }
            }
        }
        if (d1 != d2) {
            j = 1000;
            for (int i = 0; i * i <= d2; i++) {
                while (i * i + j * j > d2) {
                    j--;
                }
                if (i * i + j * j == d2) {
                    bad.add(new IntIntPair(i, j));
                    if (i != 0) {
                        bad.add(new IntIntPair(-i, j));
                        if (j != 0) {
                            bad.add(new IntIntPair(-i, -j));
                        }
                    }
                    if (j != 0) {
                        bad.add(new IntIntPair(i, -j));
                    }
                }
            }
        }
        Map<IntIntPair, Integer> points = new HashMap<>();
        for (int i = 0; i < n * n; i++) {
            if (x[i] < 0 || x[i] >= 2 * n || y[i] < 0 || y[i] >= 2 * n) {
                return new Verdict(Verdict.VerdictType.WA, "Bad point " + i);
            }
            for (IntIntPair pair : bad) {
                IntIntPair key = new IntIntPair(x[i] + pair.first, y[i] + pair.second);
                if (points.containsKey(key)) {
                    return new Verdict(Verdict.VerdictType.WA, "Bad distance " + i + " " + points.get(key));
                }
            }
            if (points.containsKey(new IntIntPair(x[i], y[i]))) {
                return Verdict.WA;
            }
            points.put(new IntIntPair(x[i], y[i]), i);
        }
        return Verdict.OK;
    }
}
