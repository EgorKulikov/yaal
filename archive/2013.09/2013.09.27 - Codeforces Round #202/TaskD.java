package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	static final long MOD = 1000000007;
	private char[][] map;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		map = IOUtils.readTable(in, rowCount, columnCount);
		if (map[0][1] == '#' || map[1][0] == '#') {
			out.printLine(0);
			return;
		}
		long answer = ways(0, 1, rowCount - 1, columnCount - 2) * ways(1, 0, rowCount - 2, columnCount - 1) -
			ways(0, 1, rowCount - 2, columnCount - 1) * ways(1, 0, rowCount - 1, columnCount - 2);
		answer = -answer;
		answer %= MOD;
		if (answer < 0)
			answer += MOD;
		out.printLine(answer);
    }

	private long ways(int fromRow, int fromColumn, int toRow, int toColumn) {
		long[][] result = new long[map.length][map[0].length];
		result[fromRow][fromColumn] = 1;
		for (int i = fromRow; i <= toRow; i++) {
			for (int j = fromColumn; j <= toColumn; j++) {
				if (map[i][j] == '#')
					continue;
				if (i != fromRow)
					result[i][j] += result[i - 1][j];
				if (j != fromColumn)
					result[i][j] += result[i][j - 1];
				if (result[i][j] >= MOD)
					result[i][j] -= MOD;
			}
		}
		return result[toRow][toColumn];
	}
}
