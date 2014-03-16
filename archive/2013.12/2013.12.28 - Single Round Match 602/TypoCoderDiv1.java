package net.egork;

import net.egork.misc.ArrayUtils;

public class TypoCoderDiv1 {
    public int getmax(int[] D, int X) {
		int count = D.length;
		int[][] answer = new int[count + 1][2200];
		ArrayUtils.fill(answer, -1);
		answer[0][X] = 0;
		int result = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < 2200; j++) {
				if (answer[i][j] == -1)
					continue;
				answer[i + 1][Math.max(j - D[i], 0)] = Math.max(answer[i + 1][Math.max(j - D[i], 0)], answer[i][j]);
				if (j + D[i] < 2200)
					answer[i + 1][j + D[i]] = Math.max(answer[i + 1][j + D[i]], answer[i][j]);
				else {
					if (i == count - 1)
						result = Math.max(result, answer[i][j] + 1);
					else if (j + D[i] - D[i + 1] < 2200)
						answer[i + 2][Math.max(j + D[i] - D[i + 1], 0)] = Math.max(answer[i + 2][Math.max(j + D[i] - D[i + 1], 0)], answer[i][j] + 2);
				}
			}
		}
		for (int i = 0; i < 2200; i++)
			result = Math.max(result, answer[count][i]);
		return result;
    }
}
