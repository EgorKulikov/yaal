package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.numbers.IntegerUtils;

import java.util.Arrays;

public class KingdomAndCities {
	final static long MOD = (long) (1e9 + 7);

	long[][] connected;
	long[] factorial;
	long[] reverseFactorial;

	public int howMany(int N, int M, int K) {
		if (N == M)
			return 0;
		if (M == 1)
			return (int) (((long) howMany(N - 1, M - 1, K - 1) * (K - 1) +
				(long) howMany(N - 1, M - 1, K - 2) * (K - 2)) % MOD);
		if (M == 2) {
			return (int) (((long) howMany(N - 1, M - 1, K - 1) * (K - 1) +
				(long) howMany(N - 1, M - 1, K - 2) * (K - 4) +
				(long) howMany(N - 2, 0, K - 3) * (N - 2)) % MOD);
		}
		if (N == 0 || K < N - 1 || K > N * (N - 1) / 2)
			return 0;
		connected = new long[N + 1][K + 1];
		ArrayUtils.fill(connected, -1);
		factorial = IntegerUtils.generateFactorial(N * (N - 1) / 2 + 1, MOD);
		reverseFactorial = IntegerUtils.generateReverseFactorials(N * (N - 1) / 2 + 1, MOD);
		return (int) countConnected(N, K);
	}

	private long countConnected(int n, int k) {
		if (connected[n][k] != -1)
			return connected[n][k];
		connected[n][k] = countAny(n, k);
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= k; j++) {
				connected[n][k] -= countConnected(i, j) * countAny(n - i, k - j) % MOD * c(n - 1, i - 1) % MOD;
			}
		}
		connected[n][k] %= MOD;
		connected[n][k] += MOD;
		connected[n][k] %= MOD;
		return connected[n][k];
	}

	private long countAny(int n, int k) {
		return c(n * (n - 1) / 2, k);
	}

	private long c(int n, int k) {
		if (k < 0 || k > n)
			return 0;
		return factorial[n] * reverseFactorial[k] % MOD * reverseFactorial[n - k] % MOD;
	}

	public static void main(String[] args) {
		KingdomAndCities solver = new KingdomAndCities();
		for (int n = 1; n <= 6; n++) {
			int length = n * (n - 1) / 2;
			boolean[][] graph = new boolean[n][n];
			int[] degree = new int[n];
			int[][] answer = new int[3][51];
			boolean[] visited = new boolean[n];
			for (int i = 0; i < (1 << length); i++) {
				ArrayUtils.fill(graph, false);
				Arrays.fill(degree, 0);
				Arrays.fill(visited, false);
				int index = 0;
				for (int j = 0; j < n; j++) {
					for (int k = j + 1; k < n; k++) {
						if (((i >> index) & 1) != 0) {
							graph[j][k] = graph[k][j] = true;
							degree[j]++;
							degree[k]++;
						}
						index++;
					}
				}
				int count = dfs(0, graph, visited);
				if (count == n) {
					answer[0][Integer.bitCount(i)]++;
					if (degree[0] == 2)
						answer[1][Integer.bitCount(i)]++;
					if (n > 1 && degree[0] == 2 && degree[1] == 2)
						answer[2][Integer.bitCount(i)]++;
				}
			}
			for (int m = 0; m <= n && m <= 2; m++) {
				for (int k = 1; k <= 50; k++) {
					if (answer[m][k] != solver.howMany(n, m, k))
						throw new RuntimeException();
				}
			}
		}
	}

	private static int dfs(int vertex, boolean[][] graph, boolean[] visited) {
		if (visited[vertex])
			return 0;
		visited[vertex] = true;
		int result = 1;
		for (int i = 0; i < graph[vertex].length; i++) {
			if (graph[vertex][i])
				result += dfs(i, graph,  visited);
		}
		return result;
	}

}

