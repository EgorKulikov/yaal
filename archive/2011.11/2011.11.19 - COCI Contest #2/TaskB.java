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
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'X')
					continue;
				int count = 0;
				for (int k = 0; k < 4; k++) {
					int row = i + MiscUtils.DX4[k];
					int column = j + MiscUtils.DY4[k];
					if (row >= 0 && row < rowCount && column >= 0 && column < columnCount && map[row][column] != 'X')
						count++;
				}
				if (count == 1) {
					out.printLine(1);
					return;
				}
			}
		}
		out.printLine(0);
	}
}
