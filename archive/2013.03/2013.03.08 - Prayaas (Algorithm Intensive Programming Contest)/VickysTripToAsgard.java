package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class VickysTripToAsgard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int breadth = in.readInt();
		int height = in.readInt();
		int[][][] cube = new int[length][breadth][height];
		for (int k = 0; k < height; k++) {
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < breadth; j++)
					cube[i][j][k] = in.readInt();
			}
		}
		int[][][][][][] answer = new int[length][breadth][height][][][];
		boolean[][][][][][] full = new boolean[length][breadth][height][][][];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < breadth; j++) {
				for (int k = 0; k < height; k++) {
					answer[i][j][k] = new int[i + 1][j + 1][k + 1];
					full[i][j][k] = new boolean[i + 1][j + 1][k + 1];
					for (int a = i; a >= 0; a--) {
						for (int b = j; b >= 0; b--) {
							for (int c = k; c >= 0; c--) {
								if (a == i && b == j && c == k) {
									answer[i][j][k][a][b][c] = cube[i][j][k];
									full[i][j][k][a][b][c] = cube[i][j][k] == 1;
								} else {
									full[i][j][k][a][b][c] = true;
									if (a != i) {
										answer[i][j][k][a][b][c] = Math.max(answer[i][j][k][a][b][c], Math.max(answer[i][j][k][a + 1][b][c], answer[i - 1][j][k][a][b][c]));
										full[i][j][k][a][b][c] &= full[i][j][k][a + 1][b][c] && full[i - 1][j][k][a][b][c];
									}
									if (b != j) {
										answer[i][j][k][a][b][c] = Math.max(answer[i][j][k][a][b][c], Math.max(answer[i][j][k][a][b + 1][c], answer[i][j - 1][k][a][b][c]));
										full[i][j][k][a][b][c] &= full[i][j][k][a][b + 1][c] && full[i][j - 1][k][a][b][c];
									}
									if (c != k) {
										answer[i][j][k][a][b][c] = Math.max(answer[i][j][k][a][b][c], Math.max(answer[i][j][k][a][b][c + 1], answer[i][j][k - 1][a][b][c]));
										full[i][j][k][a][b][c] &= full[i][j][k][a][b][c + 1] && full[i][j][k - 1][a][b][c];
									}
									if (full[i][j][k][a][b][c])
										answer[i][j][k][a][b][c] = (i - a + 1) * (j - b + 1) * (k - c + 1);
								}
							}
						}
					}
				}
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int minLength = in.readInt();
			int minBreadth = in.readInt();
			int minHeight = in.readInt();
			int maxLength = in.readInt();
			int maxBreadth = in.readInt();
			int maxHeight = in.readInt();
			out.printLine(answer[maxLength][maxBreadth][maxHeight][minLength][minBreadth][minHeight]);
		}
    }
}
