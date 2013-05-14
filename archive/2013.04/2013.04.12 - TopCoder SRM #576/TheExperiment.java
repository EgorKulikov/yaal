package net.egork;

public class TheExperiment {
	static final long MOD = (long) (1e9 + 9);

    public int countPlacements(String[] intensity, int M, int L, int A, int B) {
		int count = 0;
		for (String s : intensity)
			count += s.length();
		int[] flow = new int[count];
		int index = 0;
		for (String s : intensity) {
			for (char c : s.toCharArray())
				flow[index++] = c - '0';
		}
		int[] sumFlow = new int[count + 1];
		for (int i = 0; i < count; i++)
			sumFlow[i + 1] = sumFlow[i] + flow[i];
		long[][][] answer = new long[2][count + 1][M + 1];
		answer[0][0][0] = 1;
		for (int i = 1; i <= count; i++) {
			answer[0][i][0] = 1;
			if (i >= L && isValid(A, B, sumFlow[i] - sumFlow[i - L])) {
				for (int j = 1; j <= M; j++)
					answer[1][i][j] += answer[0][i - L][j - 1] + answer[1][i - L][j - 1];
			}
			for (int j = 0; j < i; j++) {
				for (int k = 0; k <= M; k++)
					answer[0][i][k] += answer[1][j][k];
			}
			for (int j = 1; j <= M; j++) {
				for (int k = 0; k < 2; k++) {
					for (int m = Math.max(0, i - L + 1); m < i; m++) {
						if (isValid(A, B, sumFlow[i] - sumFlow[m]))
							answer[k][i][j] += answer[k][m][j - 1];
					}
					answer[k][i][j] %= MOD;
				}
			}
		}
		long total = 0;
		for (int i = 0; i <= count; i++)
			total += answer[1][i][M];
		return (int) (total % MOD);
    }

	private boolean isValid(int from, int to, int value) {
		return from <= value && value <= to;
	}
}
