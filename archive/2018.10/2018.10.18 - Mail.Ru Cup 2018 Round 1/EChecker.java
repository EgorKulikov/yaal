package net.egork;

import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;

import static net.egork.chelper.tester.Verdict.VerdictType.WA;


public class EChecker implements Checker {
    public EChecker(String parameters) {
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
        String[][] start = in.readStringTable(n, m);
        String[][] end = in.readStringTable(n, m);
        int s = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                s += start[i][j].length();
            }
        }
        int steps = actual.readInt();
        if (steps > 4 * s) {
            return new Verdict(WA, "Too many steps");
        }
        for (int i = 0; i < steps; i++) {
            int r1 = actual.readInt() - 1;
            int c1 = actual.readInt() - 1;
            int r2 = actual.readInt() - 1;
            int c2 = actual.readInt() - 1;
            if ((r1 == r2) == (c1 == c2)) {
                return new Verdict(WA, "Bad step " + i);
            }
            if (start[r1][c1].isEmpty()) {
                return new Verdict(WA, "Bad step " + i);
            }
            int length = start[r1][c1].length();
            start[r2][c2] = start[r1][c1].substring(length - 1) + start[r2][c2];
            start[r1][c1] = start[r1][c1].substring(0, length - 1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!start[i][j].equals(end[i][j])) {
                    return new Verdict(WA, "Not same " + i + " " + j);
                }
            }
        }
        return Verdict.OK;
    }
}
