package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int d = in.readInt();
		int a = in.readInt() - 1;
		int b = in.readInt();
		double[][][][][] p = new double[d][d][3][3][3];
		double[][][] pp = new double[4][d][d];
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				for (int k = 0; k < d; k++) {
					double delta = 1d / d / d / d;
					pp[3][Math.max(i, Math.max(j, k))][Math.max(Math.min(i, j), Math.max(Math.min(i, k), Math.min(j, k)))] += delta;
				}
				double delta = 1d / d / d;
				pp[2][Math.max(i, j)][Math.min(i, j)] += delta;
			}
			pp[1][i][0] += 1d / d;
		}
		for (int i = 0; i < d; i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k < d; k++) {
					for (int l = 0; l < d; l++) {
						double delta = 1d / d / d;
						int max = Math.max(k, l);
						int min = Math.min(k, l);
						int w1 = 0;
						int w2 = 0;
						if (max >= i) {
							w1++;
						} else {
							w2++;
						}
						if (min >= j) {
							w1++;
						} else {
							w2++;
						}
						p[i][j][2][w1][w2] += delta;
					}
					double delta = 1d / d;
					int w1 = 0;
					int w2 = 0;
					if (k >= i) {
						w1++;
					} else {
						w2++;
					}
					p[i][j][1][w1][w2] += delta;
				}
			}
		}
		double[][] answer = new double[a + 1][b + 1];
		answer[1][0] = 1;
		for (int j = 1; j <= b; j++) {
			for (int k = 0; k < d; k++) {
				double p1 = answer[1][j - 1] * k / d;
				double p2 = 1;
				if (j > 1) {
					p2 = answer[1][j - 1] * k / d * k / d;
				}
				answer[1][j] += Math.min(p1, p2) / d;
			}
		}
		for (int i = 2; i <= a; i++) {
			answer[i][0] = 1;
			for (int j = 1; j <= b; j++) {
				for (int m = 0; m < d; m++) {
					for (int n = 0; n <= m; n++) {
						double p1 = 0;
						for (int k = 0; k < 3; k++) {
							for (int l = 0; l < 3; l++) {
								if (p[m][n][1][k][l] != 0) {
									p1 += answer[i - k][j - l] * p[m][n][1][k][l];
								}
							}
						}
						double p2 = 1;
						if (j > 1) {
							p2 = 0;
							for (int k = 0; k < 3; k++) {
								for (int l = 0; l < 3; l++) {
									if (p[m][n][2][k][l] != 0) {
										p2 += answer[i - k][j - l] * p[m][n][2][k][l];
									}
								}
							}
						}
						answer[i][j] += Math.min(p1, p2) * pp[Math.min(i, 3)][m][n];
					}
				}
			}
		}
		out.printLine(answer[a][b]);
	}
}
