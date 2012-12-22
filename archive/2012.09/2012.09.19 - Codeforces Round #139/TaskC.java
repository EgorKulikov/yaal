package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int minWidth = in.readInt();
		int maxWidth = in.readInt();
		char[][] table = IOUtils.readTable(in, rowCount, columnCount);
		int[] marked = new int[columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (table[i][j] == '#')
					marked[j]++;
			}
		}
		int[] taken = new int[columnCount + 1];
		int[] notTaken = new int[columnCount + 1];
		for (int i = 1; i <= columnCount; i++) {
			taken[i] = notTaken[i] = Integer.MAX_VALUE / 2;
			int currentMarked = 0;
			int currentUnmarked = 0;
			for (int j = 1; j <= maxWidth && j <= i; j++) {
				currentMarked += marked[i - j];
				currentUnmarked += rowCount - marked[i - j];
				if (j >= minWidth) {
					taken[i] = Math.min(taken[i], notTaken[i - j] + currentUnmarked);
					notTaken[i] = Math.min(notTaken[i], taken[i - j] + currentMarked);
				}
			}
		}
		out.printLine(Math.min(taken[columnCount], notTaken[columnCount]));
	}
}
