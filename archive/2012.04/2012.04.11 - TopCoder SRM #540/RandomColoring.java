package net.egork;

import net.egork.collections.ArrayUtils;

public class RandomColoring {
	double[][][] delta;
	double[][][] probability;
	int[][][] volume;
	int maxR, maxG, maxB;

	public double getProbability(int N, int maxR, int maxG, int maxB, int startR, int startG, int startB, int d1, int d2) {
		this.maxR = maxR;
		this.maxG = maxG;
		this.maxB = maxB;
		probability = new double[maxR][maxG][maxB];
		delta = new double[maxR + 1][maxG + 1][maxB + 1];
		volume = new int[maxR][maxG][maxB];
		probability[startR][startG][startB] = 1;
		for (int i = 0; i < maxR; i++) {
			for (int j = 0; j < maxG; j++) {
				for (int k = 0; k < maxB; k++) {
					int r2 = Math.min(i + d2 + 1, maxR) - Math.max(i - d2, 0);
					int r1 = Math.min(i + d1, maxR) - Math.max(i - d1 + 1, 0);
					int g2 = Math.min(j + d2 + 1, maxG) - Math.max(j - d2, 0);
					int g1 = Math.min(j + d1, maxG) - Math.max(j - d1 + 1, 0);
					int b2 = Math.min(k + d2 + 1, maxB) - Math.max(k - d2, 0);
					int b1 = Math.min(k + d1, maxB) - Math.max(k - d1 + 1, 0);
					if (d1 == 0)
						r1 = g1 = b1 = 0;
					volume[i][j][k] = r2 * g2 * b2 - r1 * g1 * b1;
				}
			}
		}
		for (int i = 1; i < N; i++)
			transition(d1, d2);
		double answer = 1;
		for (int i = Math.max(startR - d2, 0); i < Math.min(startR + d2 + 1, maxR); i++) {
			for (int k = Math.max(startG - d2, 0); k < Math.min(startG + d2 + 1, maxG); k++) {
				for (int j = Math.max(startB - d2, 0); j < Math.min(startB + d2 + 1, maxB); j++) {
					if (Math.abs(i - startR) >= d1 || Math.abs(j - startB) >= d1 || Math.abs(k - startG) >= d1)
						answer -= probability[i][k][j];
				}
			}
		}
		return answer;
	}

	private void transition(int d1, int d2) {
		ArrayUtils.fill(delta, 0.);
		for (int i = 0; i < probability.length; i++) {
			for (int j = 0; j < probability[i].length; j++) {
				for (int k = 0; k < probability[i][j].length; k++) {
					addDelta(i, j, k, d2, probability[i][j][k]);
					if (d1 != 0)
						addDelta(i, j, k, d1 - 1, -probability[i][j][k]);
				}
			}
		}
		for (int i = 0; i < probability.length; i++) {
			for (int j = 0; j < probability[i].length; j++) {
				for (int k = 0; k < probability[i][j].length; k++) {
					probability[i][j][k] = delta[i][j][k];
					if (i > 0)
						probability[i][j][k] += probability[i - 1][j][k];
					if (j > 0)
						probability[i][j][k] += probability[i][j - 1][k];
					if (k > 0)
						probability[i][j][k] += probability[i][j][k - 1];
					if (i > 0 && j > 0)
						probability[i][j][k] -= probability[i - 1][j - 1][k];
					if (i > 0 && k > 0)
						probability[i][j][k] -= probability[i - 1][j][k - 1];
					if (k > 0 && j > 0)
						probability[i][j][k] -= probability[i][j - 1][k - 1];
					if (i > 0 && j > 0 && k > 0)
						probability[i][j][k] += probability[i - 1][j - 1][k - 1];
				}
			}
		}
	}

	private void addDelta(int i, int j, int k, int d, double v) {
		if (volume[i][j][k] == 0)
			return;
		v /= volume[i][j][k];
		delta[Math.max(i - d, 0)][Math.max(j - d, 0)][Math.max(k - d, 0)] += v;
		delta[Math.max(i - d, 0)][Math.max(j - d, 0)][Math.min(k + d + 1, maxB)] -= v;
		delta[Math.max(i - d, 0)][Math.min(j + d + 1, maxG)][Math.max(k - d, 0)] -= v;
		delta[Math.min(i + d + 1, maxR)][Math.max(j - d, 0)][Math.max(k - d, 0)] -= v;
		delta[Math.max(i - d, 0)][Math.min(j + d + 1, maxG)][Math.min(k + d + 1, maxB)] += v;
		delta[Math.min(i + d + 1, maxR)][Math.max(j - d, 0)][Math.min(k + d + 1, maxB)] += v;
		delta[Math.min(i + d + 1, maxR)][Math.min(j + d + 1, maxG)][Math.max(k - d, 0)] += v;
		delta[Math.min(i + d + 1, maxR)][Math.min(j + d + 1, maxG)][Math.min(k + d + 1, maxB)] -= v;
	}
}

