package on2013_06.on2013_06_03_20_20_Hack___May_Challenge.NPuzzle;



import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;


public class NPuzzleChecker implements Checker {
    public NPuzzleChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
        return check(in, expected, actual);
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
		int size = in.readInt();
		int[][] board = IOUtils.readIntTable(in, size, size);
		int moveCount = actual.readInt();
		if (moveCount == 0)
			return Verdict.UNDECIDED;
		int row = -1;
		int column = -1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 0) {
					row = i;
					column = j;
				}
			}
		}
		for (int i = 0; i < moveCount; i++) {
			String command = actual.readString();
			if (command.charAt(0) == 'L') {
				if (column == 0)
					return new Verdict(Verdict.VerdictType.WA, "Fail on move " + i);
				board[row][column] = board[row][column - 1];
				column--;
				board[row][column] = 0;
			}
			if (command.charAt(0) == 'R') {
				if (column == size - 1)
					return new Verdict(Verdict.VerdictType.WA, "Fail on move " + i);
				board[row][column] = board[row][column + 1];
				column++;
				board[row][column] = 0;
			}
			if (command.charAt(0) == 'U') {
				if (row == 0)
					return new Verdict(Verdict.VerdictType.WA, "Fail on move " + i);
				board[row][column] = board[row - 1][column];
				row--;
				board[row][column] = 0;
			}
			if (command.charAt(0) == 'D') {
				if (row == size - 1)
					return new Verdict(Verdict.VerdictType.WA, "Fail on move " + i);
				board[row][column] = board[row + 1][column];
				row++;
				board[row][column] = 0;
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] != i * size + j)
					return Verdict.WA;
			}
		}
        return Verdict.OK;
    }
}
