package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AppleOrchard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int threshold = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int[][] sum = new int[rowCount + 1][columnCount + 1];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j];
				if (map[i][j] == 'A')
					sum[i + 1][j + 1]++;
			}
		}
		int[][] qty = new int[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = i + 1; j <= rowCount; j++) {
				for (int k = 0; k < columnCount; k++) {
					for (int l = k + 1; l <= columnCount; l++) {
						if (sum[j][l] + sum[i][k] - sum[i][l] - sum[j][k] >= threshold)
							qty[j - i - 1][l - k - 1]++;
					}
				}
			}
		}
		int[][] sumQty = new int[rowCount + 1][columnCount + 1];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++)
				sumQty[i + 1][j + 1] = sumQty[i + 1][j] + sumQty[i][j + 1] - sumQty[i][j] + qty[i][j];
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int maxRows = in.readInt();
			int maxColumns = in.readInt();
			if (maxRows > rowCount || maxColumns > columnCount)
				out.printLine(0);
			else
				out.printLine(sumQty[Math.min(rowCount, maxRows)][Math.min(columnCount, maxColumns)]);
		}
    }
}
