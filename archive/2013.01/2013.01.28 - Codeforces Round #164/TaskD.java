package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
	static final int MOD = (int) (1e9 + 9);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int height = in.readInt();
		int[][][][] result = new int[height + 1][height + 1][height + 1][height + 1];
		result[0][0][0][0] = 1;
		int[][][][] next = new int[height + 1][height + 1][height + 1][height + 1];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j <= height; j++) {
				for (int k = j; k <= height; k++)
					Arrays.fill(next[0][j][k], k, height + 1, 0);
			}
			next[height][height][height][height] = 0;
			for (int j = 1; j < height; j++) {
				next[j][height][height][height] = 0;
				for (int k = j; k < height; k++) {
					next[j][k][height][height] = 0;
					for (int l = k; l < height; l++)
						next[j][k][l][height] = 0;
				}
			}
			for (int j = 0; j <= height; j++) {
				int nj = Math.min(j + 1, height);
				for (int k = j; k <= height; k++) {
					int nk = Math.min(k + 1, height);
					for (int l = k; l <= height; l++) {
						int nl = Math.min(l + 1, height);
						int shift = 1;
						if (j != 0 && l != height)
							shift = height;
						int start = l;
						if (l != 0 && shift == height)
							start = height;
						for (int m = start; m <= height; m += shift) {
							if (result[j][k][l][m] == 0)
								continue;
							int nm = Math.min(m + 1, height);
							if (j == height) {
								next[j][nk][nl][nm] += result[j][k][l][m];
								if (next[j][nk][nl][nm] >= MOD)
									next[j][nk][nl][nm] -= MOD;
							} else {
								next[0][nk][nl][nm] += result[j][k][l][m];
								if (next[0][nk][nl][nm] >= MOD)
									next[0][nk][nl][nm] -= MOD;
							}
							if (k == height) {
								next[nj][k][nl][nm] += result[j][k][l][m];
								if (next[nj][k][nl][nm] >= MOD)
									next[nj][k][nl][nm] -= MOD;
							} else {
								next[0][nj][nl][nm] += result[j][k][l][m];
								if (next[0][nj][nl][nm] >= MOD)
									next[0][nj][nl][nm] -= MOD;
							}
							if (l == height) {
								next[nj][nk][l][nm] += result[j][k][l][m];
								if (next[nj][nk][l][nm] >= MOD)
									next[nj][nk][l][nm] -= MOD;
							} else {
								next[0][nj][nk][nm] += result[j][k][l][m];
								if (next[0][nj][nk][nm] >= MOD)
									next[0][nj][nk][nm] -= MOD;
							}
							if (m == height) {
								next[nj][nk][nl][m] += result[j][k][l][m];
								if (next[nj][nk][nl][m] >= MOD)
									next[nj][nk][nl][m] -= MOD;
							} else {
								next[0][nj][nk][nl] += result[j][k][l][m];
								if (next[0][nj][nk][nl] >= MOD)
									next[0][nj][nk][nl] -= MOD;
							}
						}
					}
				}
			}
			int[][][][] temp = result;
			result = next;
			next = temp;
		}
		long total = 0;
		for (int i = 0; i <= height; i++) {
			for (int j = i; j <= height; j++) {
				for (int k = j; k <= height; k++) {
					for (int l = k; l <= height; l++) {
						int delta;
						if (i == l)
							delta = 1;
						else if (j == l)
							delta = 4;
						else if (k == l)
							delta = 12;
						else
							delta = 24;
						total += result[i][j][k][l];
					}
				}
			}
		}
		total -= result[height][height][height][height];
		out.printLine(total % MOD);
    }
}
