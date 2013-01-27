package net.egork;

public class FoxAndGreed {
	private static final int MOD = 10007;

	public int count(int H, int W, int S) {
		int[][] count = new int[W][S + 1];
		int[][] sum = new int[W][S + 1];
		count[W - 1][0] = 1;
		if (H <= S + 2) {
			for (int i = W - 2; i >= 0; i--) {
				for (int j = 0; j <= S; j++) {
					for (int k = 0; k <= j; k++)
						count[i][j] += count[i + 1][k];
					count[i][j] %= MOD;
				}
			}
		} else {
			for (int i = S + 2; i < H; i++) {
				for (int j = S; j >= 0; j--) {
					for (int k = 0; k < j; k++)
						count[W - 1][j] += count[W - 1][k];
					count[W - 1][j] %= MOD;
				}
			}
		}
		int[] p = new int[Math.max(H, W) + 1];
		p[0] = 1;
		for (int i = 1; i < p.length; i++)
			p[i] = p[i - 1] * (S + 1) % MOD;
		for (int i = 0; i < W; i++) {
			int curSum = 0;
			int result = 0;
			for (int k = 0; k <= S; k++) {
				curSum += count[i][k];
				result += curSum;
				result %= MOD;
				sum[i][k] = result;
			}
		}
		for (int i = Math.min(H - 2, S); i >= 0; i--) {
			for (int j = S; j >= 0; j--) {
				for (int k = 0; k < j; k++)
					count[W - 1][j] += count[W - 1][k];
				count[W - 1][j] %= MOD;
			}
			for (int j = W - 2; j >= 0; j--) {
				int curSum = 0;
				int result = 0;
				for (int k = 0; k <= S; k++) {
					curSum += count[j + 1][k];
					result += curSum;
					result %= MOD;
					sum[j + 1][k] = result;
				}
				for (int k = S; k >= 0; k--) {
					count[j][k] = 0;
					if (k > 0)
						count[j][k] += sum[j][k - 1] * p[W - j - 2];
					count[j][k] += sum[j + 1][k] * p[H - i - 2];
					count[j][k] %= MOD;
				}
			}
			int curSum = 0;
			int result = 0;
			for (int k = 0; k <= S; k++) {
				curSum += count[0][k];
				result += curSum;
				result %= MOD;
				sum[0][k] = result;
			}
		}
		return count[0][S];
	}


}

