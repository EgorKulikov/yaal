package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskK {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[][] q = new int[2][n];
		for (int i = 0; i < n; i++) {
			q[0][i] = in.readInt() - n - 1;
		}
		for (int i = 0; i < n; i++) {
			q[1][i] = in.readInt() - 1;
		}
		int[][] d = new int[2][n];
//		boolean[][] z = new boolean[2][n];
		int[][] r = new int[2][n];
		int[][] s = new int[2][n];
		int[] nn = new int[2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				d[1 - j][q[j][i]]++;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				if (d[j][i] == 0) {
					s[j][nn[j]++] = i;
				}
			}
		}
		while (nn[0] > 0 && nn[1] > 0) {
			for (int j = 0; j < 2; j++) {
				int x = s[j][--nn[j]];
				r[j][x] = 1;
				int y = q[j][x];
				if (r[1 - j][y] == 0) {
					r[1 - j][y] = 2;
					int z = q[1 - j][y];
					d[j][z]--;
					if (d[j][z] == 0 && r[j][z] == 0) {
						s[j][nn[j]++] = z;
					}
				}
			}
		}
		{
			int j = nn[0] == 0 ? 1 : 0;
			for (int i = 0; i < n; i++) {
				if (r[j][i] == 0) r[j][i] = 1;
			}
		}
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < n; i++) {
				if (r[j][i] == 1) {
					out.print(j * n + i + 1);
					out.print(" ");
				}
			}

		}
	}
}
