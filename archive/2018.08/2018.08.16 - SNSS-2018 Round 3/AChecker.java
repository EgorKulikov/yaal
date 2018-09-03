package net.egork;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.ListIndependentSetSystem;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.io.InputReader;
import net.egork.misc.MiscUtils;

import java.io.StringBufferInputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;


public class AChecker implements Checker {
    public AChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null) {
            expected = null;
        } else {
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        }
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
        try {
            return check(in, expected, actual);
        } catch (InputMismatchException e) {
            return new Verdict(Verdict.VerdictType.PE, e.getMessage());
        }
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
        int t = in.readInt();
        for (int i = 0; i < t; i++) {
            int n = in.readInt();
            int m = in.readInt();
            int[] x = new int[m];
            int[] y = new int[m];
            in.readIntArrays(x, y);
            Set<IntIntPair> edges = new HashSet<>();
            for (int j = 0; j < m; j++) {
                edges.add(new IntIntPair(x[j], y[j]));
                edges.add(new IntIntPair(y[j], x[j]));
            }
            boolean hasAnswer = hasAnswer(n, m, x, y);
            int r = actual.readInt();
            if (r == -1) {
                if (hasAnswer) {
                    return new Verdict(Verdict.VerdictType.WA, "Incorrect -1");
                }
                continue;
            }
            int[] degree = new int[n + 1];
            for (int j = 0; j < r; j++) {
                int a = actual.readInt();
                int b = actual.readInt();
                degree[a]++;
                degree[b]++;
                if (!edges.contains(new IntIntPair(a, b))) {
                    return new Verdict(Verdict.VerdictType.WA, "Unknown edge " + a + " " + b);
                }
            }
            for (int j = 1; j <= n; j++) {
                if (degree[j] % 2 == 0) {
                    return new Verdict(Verdict.VerdictType.WA, "Even degree at vertex " + j);
                }
            }
        }
        return Verdict.OK;
    }

    private boolean hasAnswer(int n, int m, int[] x, int[] y) {
        MiscUtils.decreaseByOne(x, y);
        int[] degree = new int[n];
        for (int i : x) {
            degree[i]++;
        }
        for (int i : y) {
            degree[i]++;
        }
        IndependentSetSystem setSystem = new ListIndependentSetSystem(n);
        for (int i = 0; i < m; i++) {
            setSystem.join(x[i], y[i]);
        }
        int[] qty = new int[n];
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                return false;
            }
            if (degree[i] % 2 == 0) {
                qty[setSystem.get(i)]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (qty[i] % 2 == 1) {
                return false;
            }
        }
        return true;
    }
}
