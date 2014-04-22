package net.egork;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class TaskCChecker implements Checker {
    public TaskCChecker(String parameters) {
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
		int testCount = in.readInt();
		for (int i = 0; i < testCount; i++) {
			int rowCount = in.readInt();
			int columnCount = in.readInt();
			int mineCount = in.readInt();
			actual.readLine();
			char[][] map = new char[rowCount][];
			for (int j = 0; j < rowCount; j++) {
				String s = actual.readLine();
				if (s.equals("Impossible")) {
					map = null;
					break;
				}
				map[j] = s.toCharArray();
			}
			if (map == null)
				continue;
			int totalMines = 0;
			int cCount = 0;
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (map[j][k] == '*')
						totalMines++;
					if (map[j][k] == 'c')
						cCount++;
				}
			}
			if (mineCount != totalMines)
				return new Verdict(Verdict.VerdictType.WA, rowCount + " " + columnCount + " " + mineCount + " incorrect amount of mines");
			if (cCount != 1)
				return new Verdict(Verdict.VerdictType.WA, rowCount + " " + columnCount + " " + mineCount + " incorrect amount of clicks");
			if (mineCount == rowCount * columnCount - 1)
				continue;
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (map[j][k] == '*')
						continue;
					boolean good = true;
					for (int dj = -1; dj <= 1; dj++) {
						for (int dk = -1; dk <= 1; dk++) {
							int nj = j + dj;
							int nk = k + dk;
							if (MiscUtils.isValidCell(nj, nk, rowCount, columnCount) && map[nj][nk] == '*')
								good = false;
						}
					}
					if (good)
						map[j][k] = '0';
				}
			}
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					if (map[j][k] == 'c')
						return new Verdict(Verdict.VerdictType.WA, rowCount + " " + columnCount + " " + mineCount + " click not good");
					if (map[j][k] != '.')
						continue;
					boolean good = false;
					for (int dj = -1; dj <= 1; dj++) {
						for (int dk = -1; dk <= 1; dk++) {
							int nj = j + dj;
							int nk = k + dk;
							if (MiscUtils.isValidCell(nj, nk, rowCount, columnCount) && map[nj][nk] == '0')
								good = true;
						}
					}
					if (!good)
						return new Verdict(Verdict.VerdictType.WA, rowCount + " " + columnCount + " " + mineCount + " not good cell");
				}
			}
		}
        return Verdict.OK;
    }
}
