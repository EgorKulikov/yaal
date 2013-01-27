package net.egork;

public class PatrolRoute {
	private static final long MOD = 1000000007;

	public int countRoutes(int X, int Y, int minT, int maxT) {
		long[] countX = go(X);
		long[] countY = go(Y);
		long answer = 0;
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				int time = 2 * (i + j);
				if (time >= minT && time <= maxT)
					answer += countX[i] * countY[j] % MOD;
			}
		}
		answer *= 6;
		return (int) (answer % MOD);
	}

	private long[] go(int upTo) {
		long[] result = new long[upTo];
		for (int i = 0; i < upTo; i++) {
			for (int j = i + 2; j < upTo; j++)
				result[j - i] += j - i - 1;
		}
		return result;
	}


}

