package net.egork;

public class BearSpans {
	public int[] findAnyGraph(int n, int m, int k) {
		int[] answer = new int[2 * m];
		int at = 0;
		boolean[][] used = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			used[i][i] = true;
		}
		int remaining = n;
		int step = 1;
		while (true) {
			if (remaining == 1) {
				return new int[]{-1};
			}
			if (k == 1) {
				for (int j = 1; j < remaining; j++) {
					answer[at++] = 1;
					answer[at++] = j * step + 1;
					used[0][j * step] = used[j * step][0] = true;
				}
				break;
			}
			for (int j = 0; j + 1 < remaining; j += 2) {
				answer[at++] = j * step + 1;
				answer[at++] = (j + 1) * step + 1;
				used[j * step][(j + 1) * step] = used[(j + 1) * step][j * step] = true;
			}
			if (remaining % 2 == 1) {
				answer[at++] = 1;
				answer[at++] = (remaining - 1) * step + 1;
				used[0][(remaining - 1) * step] = used[(remaining - 1) * step][0] = true;
			}
			remaining /= 2;
			k--;
			step *= 2;
		}
		for (int i = 0; i < n && at < answer.length; i++) {
			for (int j = 0; j < i; j++) {
				if (!used[i][j]) {
					used[i][j] = used[j][i] = false;
					answer[at++] = i + 1;
					answer[at++] = j + 1;
					if (at == answer.length) {
						break;
					}
				}
			}
		}
		return answer;
	}
}
