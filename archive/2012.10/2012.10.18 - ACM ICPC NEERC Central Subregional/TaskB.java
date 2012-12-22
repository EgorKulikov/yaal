package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		int answer = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == '-')
					continue;
				for (int k = 0; k < 4; k++) {
					int ni = i + MiscUtils.DX4[k];
					int nj = j + MiscUtils.DY4[k];
					if (ni >= 0 && ni < rowCount && nj >= 0 && nj < columnCount && map[ni][nj] == '-') {
						answer++;
						break;
					}
				}
			}
		}
		out.printLine(answer);
	}
}
