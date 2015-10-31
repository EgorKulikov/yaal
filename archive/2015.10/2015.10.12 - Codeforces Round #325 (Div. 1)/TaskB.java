package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		in.readInt();
		char[][] map = IOUtils.readTable(in, 3, n);
		boolean[][] reachable = new boolean[3][n];
		for (int i = 0; i < 3; i++) {
			if (map[i][0] == 's') {
				reachable[i][0] = true;
			}
		}
		boolean answer = false;
		for (int i = 0; i < n; i += 3) {
			for (int j = 0; j < 3; j++) {
				if (reachable[j][i]) {
					if (i + 1 >= n) {
						answer = true;
					} else if (map[j][i + 1] == '.') {
						for (int k = Math.max(j - 1, 0); k <= Math.min(j + 1, 2); k++) {
							boolean good = true;
							for (int l = 1; l <= 3; l++) {
								if (i + l >= n) {
									answer = true;
									good = false;
								} else if (map[k][i + l] != '.') {
									good = false;
									break;
								}
							}
							if (good) {
								reachable[k][i + 3] = true;
							}
						}
					}
				}
			}
		}
		if (answer) {
			out.printLine("YES");
		} else {
			out.printLine("NO");
		}
	}
}
