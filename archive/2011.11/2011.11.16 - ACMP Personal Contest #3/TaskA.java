package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] negative = IOUtils.readTable(in, rowCount, columnCount);
		char[][] positive = IOUtils.readTable(in, rowCount, columnCount);
		int answer = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (negative[i][j] == positive[i][j])
					answer++;
			}
		}
		out.printLine(answer);
	}
}
