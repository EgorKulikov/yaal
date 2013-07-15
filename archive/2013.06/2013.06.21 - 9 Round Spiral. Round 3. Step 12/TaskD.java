package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int count = in.readInt();
		int[] son = IOUtils.readIntArray(in, count);
		int k = 0;
		int[][] answer = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			if ((i & 1) == 0) {
				for (int j = 0; j < columnCount; j++) {
					answer[i][j] = k + 1;
					if (--son[k] == 0)
						k++;
				}
			} else {
				for (int j = columnCount - 1; j >= 0; j--) {
					answer[i][j] = k + 1;
					if (--son[k] == 0)
						k++;
				}
			}
		}
		for (int[] row : answer)
			out.printLine(row);
    }
}
