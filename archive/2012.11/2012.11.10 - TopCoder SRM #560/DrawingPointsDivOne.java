package net.egork;

import net.egork.collections.ArrayUtils;

public class DrawingPointsDivOne {
	public int maxSteps(int[] x, int[] y) {
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < x.length; i++) {
			x[i] += 70;
			y[i] += 70;
		}
		int[][] time = new int[281][281];
		ArrayUtils.fill(time, Integer.MAX_VALUE);
		for (int i = 0; i < x.length; i++) {
			for (int j = x[i]; j <= 280; j++) {
				for (int k = y[i]; k <= 280; k++)
					time[j][k] = Math.min(time[j][k], Math.max(j - x[i], k - y[i]));
			}
		}
		for (int i = 0; i <= 140; i++) {
			for (int j = 0; j <= 140; j++) {
				if (time[i][j] == 0)
					continue;
				int max = 0;
				for (int k = 0; i + k <= 280 && j + k <= 280; k++) {
					for (int l = i; l <= i + k; l++) {
						max = Math.max(max, time[l][j + k]);
					}
					for (int l = j; l < j + k; l++) {
						max = Math.max(max, time[i + k][l]);
					}
					if (max <= k) {
						answer = Math.min(answer, k - 1);
						break;
					}
				}
			}
		}
		if (answer == Integer.MAX_VALUE)
			return -1;
		return answer;
	}
}
