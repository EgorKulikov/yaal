package on2014_09.on2014_09_28_Codeforces_Round__270.E___Design_Tutorial__Learn_from_a_Game;



import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;
import java.util.InputMismatchException;


public class TaskEChecker implements Checker {
    public TaskEChecker(String parameters) {
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
		int steps = actual.readInt();
		boolean can = expected.readInt() != -1;
		if ((steps != -1) ^ can) {
			return Verdict.WA;
		}
		if (steps == -1) {
			return Verdict.OK;
		}
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] start = IOUtils.readIntTable(in, rowCount, columnCount);
		int[][] target = IOUtils.readIntTable(in, rowCount, columnCount);
		int sx = actual.readInt() - 1;
		int sy = actual.readInt() - 1;
		for (int i = 0; i < steps; i++) {
			int nx = actual.readInt() - 1;
			int ny = actual.readInt() - 1;
			if (sx == nx && sy == ny || Math.abs(sx - nx) > 1 || Math.abs(sy - ny) > 1) {
				return Verdict.WA;
			}
			int temp = start[nx][ny];
			start[nx][ny] = start[sx][sy];
			start[sx][sy] = temp;
			sx = nx;
			sy = ny;
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (start[i][j] != target[i][j]) {
					return Verdict.WA;
				}
			}
		}
		return Verdict.OK;
    }
}
