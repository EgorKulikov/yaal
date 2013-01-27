package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int column = 0;
		long answer = 0;
		boolean direction = true;
		for (int row = 0; row < rowCount - 1; row++) {
			int nextLeft = 0;
			int nextRight = columnCount - 1;
			for (int j = column; j < columnCount - 1; j++) {
				if (map[row + 1][j] == '.' || map[row][j + 1] != '.') {
					nextRight = j;
					break;
				}
			}
			for (int j = column; j > 0; j--) {
				if (map[row + 1][j] == '.' || map[row][j - 1] != '.') {
					nextLeft = j;
					break;
				}
			}
			int sinceBreak = 0;
			while (sinceBreak < 3) {
				sinceBreak++;
				if (direction) {
					answer += nextRight - column;
					column = nextRight;
				} else {
					answer += column - nextLeft;
					column = nextLeft;
				}
				answer++;
				if (map[row + 1][column] == '.')
					break;
				if (direction) {
					if (column != columnCount - 1 && map[row][column + 1] == '+') {
						map[row][column + 1] = '.';
						sinceBreak = 0;
						nextRight = columnCount - 1;
						for (int j = column + 1; j < columnCount - 1; j++) {
							if (map[row + 1][j] == '.' || map[row][j + 1] != '.') {
								nextRight = j;
								break;
							}
						}
					}
				} else {
					if (column != 0 && map[row][column - 1] == '+') {
						map[row][column - 1] = '.';
						sinceBreak = 0;
						nextLeft = 0;
						for (int j = column - 1; j > 0; j--) {
							if (map[row + 1][j] == '.' || map[row][j - 1] != '.') {
								nextLeft = j;
								break;
							}
						}
					}
				}
				direction = !direction;
			}
			if (sinceBreak == 3) {
				out.printLine("Never");
				return;
			}
		}
		out.printLine(answer);
	}
}
