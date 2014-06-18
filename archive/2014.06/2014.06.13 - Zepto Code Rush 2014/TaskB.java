package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int[] answer = new int[columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'U') {
					if ((i & 1) == 0)
						answer[j]++;
				} else if (map[i][j] == 'L') {
					if (j >= i)
						answer[j - i]++;
				} else if (map[i][j] == 'R') {
					if (j + i < columnCount)
						answer[j + i]++;
				}
			}
		}
		out.printLine(answer);
	}
}
