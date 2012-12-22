package net.egork;

import java.util.Arrays;

public class P8XGraphBuilder {
	public int solve(int[] scores) {
		int count = scores.length + 1;
		int[][] answer = new int[count + 1][2 * count - 1];
		Arrays.fill(answer[0], Integer.MIN_VALUE);
		answer[0][0] = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i; j < 2 * count - 2; j++) {
				for (int k = 0; k < count - 1 && j + k + 1 <= 2 * count - 2; k++)
					answer[i + 1][j + k + 1] = Math.max(answer[i + 1][j + k + 1], answer[i][j] + scores[k]);
			}
		}
		return answer[count][2 * count - 2];
	}


}

