package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int answer = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				for (int k = 0; ; k++) {
					if (k > i || k > j || i + k >= rowCount || j + k >= columnCount || map[i - k][j - k] != 'X' ||
						map[i - k][j + k] != 'X' || map[i + k][j - k] != 'X' || map[i + k][j + k] != 'X')
					{
						answer = Math.max(answer, k);
						break;
					}
				}
			}
		}
		out.printLine(answer);
    }
}
