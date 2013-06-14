package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int minStars = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		if (rowCount < 3 || columnCount < 3) {
			out.printLine(0);
			return;
		}
		boolean[][] isStar = new boolean[rowCount - 2][columnCount - 2];
		for (int i = 1; i < rowCount - 1; i++) {
			for (int j = 1; j < columnCount - 1; j++) {
				if (map[i][j] == '0' || map[i + 1][j] == '0' || map[i - 1][j] == '0' || map[i][j + 1] == '0' ||
					map[i][j - 1] == '0')
				{
					continue;
				}
				isStar[i - 1][j - 1] = true;
			}
		}
		rowCount -= 2;
		columnCount -= 2;
		int[][] countStars = new int[rowCount + 1][columnCount + 1];
		for (int i = 0; i < rowCount; i++) {
			int row = 0;
			for (int j = 0; j < columnCount; j++) {
				if (isStar[i][j])
					row++;
				countStars[i + 1][j + 1] = row + countStars[i][j + 1];
			}
		}
		long answer = 0;
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 1; j <= columnCount; j++) {
				int column = j;
				for (int k = 0; k < i; k++) {
					while (column >= 0 && countStars[i][j] + countStars[k][column] - countStars[i][column] - countStars[k][j] < minStars)
						column--;
					answer += column + 1;
				}
			}
		}
		out.printLine(answer);
	}
}
