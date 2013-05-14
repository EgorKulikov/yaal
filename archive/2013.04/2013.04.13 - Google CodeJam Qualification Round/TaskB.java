package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int[][] target = IOUtils.readIntTable(in, rowCount, columnCount);
		int[][] test = new int[rowCount][columnCount];
		ArrayUtils.fill(test, 100);
		for (int i = 0; i < rowCount; i++) {
			int max = 0;
			for (int j = 0; j < columnCount; j++)
				max = Math.max(max, target[i][j]);
			for (int j = 0; j < columnCount; j++)
				test[i][j] = Math.min(test[i][j], max);
		}
		for (int i = 0; i < columnCount; i++) {
			int max = 0;
			for (int j = 0; j < rowCount; j++)
				max = Math.max(max, target[j][i]);
			for (int j = 0; j < rowCount; j++)
				test[j][i] = Math.min(test[j][i], max);
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (target[i][j] != test[i][j]) {
					out.printLine("Case #" + testNumber + ": NO");
					return;
				}
			}
		}
		out.printLine("Case #" + testNumber + ": YES");
    }
}
