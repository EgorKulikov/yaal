package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;

import static java.lang.Math.abs;
import static net.egork.misc.ArrayUtils.fill;


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
        int[] sx = new int[m];
        int[] sy = new int[m];
        in.readIntArrays(sx, sy);
        int[] fx = new int[m];
        int[] fy = new int[m];
        in.readIntArrays(fx, fy);
        int[][] map = new int[n][n];
        fill(map, -1);
        for (int i = 0; i < m; i++) {
            map[sx[i] - 1][sy[i] - 1] = i;
        }
        int moves = actual.readInt();
        if (moves > 10800) {
            return new Verdict(Verdict.VerdictType.WA, "Too many moves");
        }
        for (int i = 0; i < moves; i++) {
            int ax = actual.readInt() - 1;
            int ay = actual.readInt() - 1;
            int bx = actual.readInt() - 1;
            int by = actual.readInt() - 1;
            if (abs(ax - bx) + abs(ay - by) != 1) {
                return new Verdict(Verdict.VerdictType.WA, "Invalid move");
            }
            if (map[ax][ay] == -1) {
                return new Verdict(Verdict.VerdictType.WA, "No piece here");
            }
            if (map[bx][by] != -1) {
                return new Verdict(Verdict.VerdictType.WA, "Cell occupied");
            }
            map[bx][by] = map[ax][ay];
            map[ax][ay] = -1;
        }
        for (int i = 0; i < m; i++) {
            if (map[fx[i] - 1][fy[i] - 1] != i) {
                return new Verdict(Verdict.VerdictType.WA, "Incorrect final position");
            }
        }
        return Verdict.OK;
    }
}
