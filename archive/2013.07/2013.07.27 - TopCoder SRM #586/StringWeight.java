package net.egork;

import net.egork.misc.ArrayUtils;

public class StringWeight {
    public int getMinimum(int[] L) {
		int count = L.length;
		int[][][] result = new int[count + 1][27][27];
		ArrayUtils.fill(result, Integer.MAX_VALUE / 2);
		result[0][0][0] = 0;
		for (int i = 0; i < count; i++) {
			int current = L[i];
			for (int j = 0; j <= 26; j++) {
				for (int k = 0; k <= j; k++) {
					for (int l = 0; j + l <= 26; l++) {
						for (int m = 0; m + k <= j + l; m++) {
							if (j + l - k < Math.min(current, 26))
								continue;
							int addCost = 0;
							int openClosed = Math.min(j - k, m);
							addCost += openClosed * (openClosed + 1) / 2;
							int stillOpen = Math.max(0, j - k - m);
							addCost += stillOpen * current;
							int newlyOpened = Math.min(l, j + l - (k + m));
							addCost += newlyOpened * (newlyOpened - 1) / 2;
							if (stillOpen == 0)
								addCost += Math.max(current - 26, 0);
							result[i + 1][j + l][k + m] = Math.min(result[i + 1][j + l][k + m], result[i][j][k] + addCost);
						}
					}
				}
			}
		}
		int answer = Integer.MAX_VALUE / 2;
		for (int[] row : result[count])
			answer = Math.min(answer, ArrayUtils.minElement(row));
		return answer;
	}
}
