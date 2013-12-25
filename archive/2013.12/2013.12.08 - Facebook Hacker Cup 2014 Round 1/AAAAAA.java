package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AAAAAA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		char[][] map = IOUtils.readTable(in, rowCount, columnCount);
		boolean[][] reachable = new boolean[rowCount][columnCount];
		reachable[0][0] = true;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == '#')
					continue;
				if (i != 0 && reachable[i - 1][j])
					reachable[i][j] = true;
				if (j != 0 && reachable[i][j - 1])
					reachable[i][j] = true;
			}
		}
		int[][] max = new int[rowCount + 1][columnCount + 1];
		for (int i = rowCount - 1; i >= 0; i--) {
			for (int j = columnCount - 1; j >= 0; j--) {
				if (map[i][j] == '#')
					continue;
				max[i][j] = 1 + Math.max(max[i + 1][j], max[i][j + 1]);
			}
		}
		int answer = max[0][0];
		for (int i = 1; i < rowCount; i++) {
			boolean has = false;
			int at = -1;
			for (int j = columnCount - 1; j >= 0; j--) {
				if (map[i][j] == '#') {
					has = false;
					continue;
				}
				if (!has && reachable[i - 1][j]) {
					has = true;
					at = j;
				}
				if (has)
					answer = Math.max(answer, i + at + at - j + 1 + max[i + 1][j]);
			}
		}
		for (int i = 1; i < columnCount; i++) {
			boolean has = false;
			int at = -1;
			for (int j = rowCount - 1; j >= 0; j--) {
				if (map[j][i] == '#') {
					has = false;
					continue;
				}
				if (!has && reachable[j][i - 1]) {
					has = true;
					at = j;
				}
				if (has)
					answer = Math.max(answer, i + at + at - j + 1 + max[j][i + 1]);
			}
		}
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
