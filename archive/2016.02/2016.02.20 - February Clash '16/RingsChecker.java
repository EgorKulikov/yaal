package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.generated.collections.list.DoubleArrayList;
import net.egork.generated.collections.list.DoubleList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class RingsChecker implements Checker {
    static double totalScore = 0;
    static DoubleList scores = new DoubleArrayList();

    public RingsChecker(String parameters) {
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
        int c = in.readInt();
        int[][] rings = IOUtils.readIntTable(in, n, m);
        int answer = actual.readInt();
        int[] id = new int[answer];
        int[] shift = new int[answer];
        IOUtils.readIntArrays(actual, id, shift);
        boolean[] used = new boolean[n];
        boolean[][] was = new boolean[m][c];
        for (int i = 0; i < answer; i++) {
            if (used[id[i]] || shift[i] < 0 || shift[i] >= m) {
                return Verdict.WA;
            }
            used[id[i]] = true;
            for (int j = 0; j < m; j++) {
                int ind = j - shift[i];
                if (ind < 0) {
                    ind += m;
                }
                if (was[ind][rings[id[i]][j]]) {
                    return Verdict.WA;
                }
                was[ind][rings[id[i]][j]] = true;
            }
        }
        double score = answer * 10000d / n;
        totalScore += score;
        StringBuilder comment = new StringBuilder();
        comment.append(String.format("Score = %.3f, total score = %.3f", score, totalScore));
        scores.add(score);
        for (double d : scores) {
            comment.append('\n');
            comment.append(String.format("%.3f", d));
        }
        return new Verdict(Verdict.VerdictType.OK, comment.toString());
    }
}
