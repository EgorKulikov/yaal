package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		char[][] map = IOUtils.readTable(in, size, size);
		int[][] max = new int[size][size];
		long answer = 0;
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] == '.') {
					continue;
				}
				if (j > 0 && j < size - 1 && i < size - 1) {
					max[i][j] = Math.min(Math.min(max[i + 1][j - 1], max[i + 1][j + 1]), max[i + 1][j]) + 1;
				} else {
					max[i][j] = 1;
				}
				answer += max[i][j];
			}
		}
		out.printLine(answer);
    }
}
