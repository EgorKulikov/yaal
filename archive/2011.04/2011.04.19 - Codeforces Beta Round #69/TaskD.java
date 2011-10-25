package April2011.CodeforcesBetaRound69;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	private char[][][] horizontal = {
		{
			{'.', '.', '.'},
			{'.', '.', '.'},
			{'.', '.', '.'},
		},
		{
			{'.', '.', '.'},
			{'.', 'O', '.'},
			{'.', '.', '.'},
		},
		{
			{'O', '.', '.'},
			{'.', '.', '.'},
			{'.', '.', 'O'},
		},
		{
			{'O', '.', '.'},
			{'.', 'O', '.'},
			{'.', '.', 'O'},
		},
		{
			{'O', '.', 'O'},
			{'.', '.', '.'},
			{'O', '.', 'O'},
		},
		{
			{'O', '.', 'O'},
			{'.', 'O', '.'},
			{'O', '.', 'O'},
		},
		{
			{'O', 'O', 'O'},
			{'.', '.', '.'},
			{'O', 'O', 'O'},
		},
	};

	private char[][][] vertical = {
		{
			{'.', '.', '.'},
			{'.', '.', '.'},
			{'.', '.', '.'},
		},
		{
			{'.', '.', '.'},
			{'.', 'O', '.'},
			{'.', '.', '.'},
		},
		{
			{'.', '.', 'O'},
			{'.', '.', '.'},
			{'O', '.', '.'},
		},
		{
			{'.', '.', 'O'},
			{'.', 'O', '.'},
			{'O', '.', '.'},
		},
		{
			{'O', '.', 'O'},
			{'.', '.', '.'},
			{'O', '.', 'O'},
		},
		{
			{'O', '.', 'O'},
			{'.', 'O', '.'},
			{'O', '.', 'O'},
		},
		{
			{'O', '.', 'O'},
			{'O', '.', 'O'},
			{'O', '.', 'O'},
		},
	};
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] table = in.readTable(4 * rowCount + 1, 4 * columnCount + 1);
		int[][] type = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				for (char[][] domino : vertical) {
					boolean valid = true;
					for (int k = 0; k < 3; k++) {
						for (int l = 0; l < 3; l++) {
							if (table[4 * i + 1 + k] [4 * j + 1 + l] != domino[k][l])
								valid = false;
						}
					}
					if (valid)
						type[i][j]++;
				}
				for (char[][] domino : horizontal) {
					boolean valid = true;
					for (int k = 0; k < 3; k++) {
						for (int l = 0; l < 3; l++) {
							if (table[4 * i + 1 + k] [4 * j + 1 + l] != domino[k][l])
								valid = false;
						}
					}
					if (valid)
						type[i][j] += 2;
				}
			}
		}
		long[] answer = new long[columnCount + 1];
		answer[0] = 1;
		for (int i = 0; i < columnCount; i++) {
			boolean isVerticalRow = true;
			for (int j = 0; j < rowCount && isVerticalRow; j++) {
				if (type[j][i] == 2)
					isVerticalRow = false;
			}
			if (isVerticalRow && rowCount % 2 == 0)
				answer[i + 1] = (answer[i + 1] + answer[i]) % MOD;
			if (i == columnCount - 1)
				continue;
			long[] count = new long[2];
			long[] nextCount = new long[2];
			count[0] = 1;
			boolean isSecondRowVertical = true;
			for (int j = 0; j < rowCount && isSecondRowVertical; j++) {
				if (type[j][i + 1] == 2)
					isSecondRowVertical = false;
			}
			for (int j = 0; j < rowCount; j++) {
				nextCount[0] = nextCount[1] = 0;
				if (type[j][i] >= 2 && type[j][i + 1] >= 2)
					nextCount[0] = count[0];
				nextCount[0] += count[1];
				if (j == rowCount - 1) {
					long[] temp = nextCount;
					nextCount = count;
					count = temp;
					continue;
				}
				boolean firstVertical = (type[j][i] & 1) != 0 && (type[j + 1][i] & 1) != 0;
				boolean secondVertical = (type[j][i + 1] & 1) != 0 && (type[j + 1][i + 1] & 1) != 0;
				if (firstVertical && secondVertical)
					nextCount[1] += count[0];
				nextCount[0] %= MOD;
				nextCount[1] %= MOD;
				long[] temp = nextCount;
				nextCount = count;
				count = temp;
			}
			if (isVerticalRow && isSecondRowVertical && rowCount % 2 == 0)
				count[0] = (count[0] + MOD - 1) % MOD;
			answer[i + 2] = (answer[i] * count[0]) % MOD;
		}
		out.println(answer[columnCount]);
	}
}

