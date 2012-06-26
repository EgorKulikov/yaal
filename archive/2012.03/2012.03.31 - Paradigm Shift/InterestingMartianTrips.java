package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class InterestingMartianTrips {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] heights = IOUtils.readIntTable(in, rowCount, columnCount);
		long result = 0;
		for (int k = 0; k < 2; k++) {
			long[][] answer = new long[rowCount][columnCount];
			answer[0][0] = heights[0][0];
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					if (i != 0 && heights[i][j] != heights[i - 1][j] && (((i + j) % 2 == k) ^ (heights[i][j] > heights[i - 1][j])) && answer[i - 1][j] != 0)
						answer[i][j] = Math.max(answer[i][j], answer[i - 1][j] + heights[i][j]);
					if (j != 0 && heights[i][j] != heights[i][j - 1] && (((i + j) % 2 == k) ^ (heights[i][j] > heights[i][j - 1])) && answer[i][j - 1] != 0)
						answer[i][j] = Math.max(answer[i][j], answer[i][j - 1] + heights[i][j]);
					result = Math.max(result, answer[i][j]);
				}
			}
		}
		out.printLine(result);
	}
}
