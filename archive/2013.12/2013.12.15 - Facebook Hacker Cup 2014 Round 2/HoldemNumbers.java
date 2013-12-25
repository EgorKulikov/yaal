package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HoldemNumbers {
//	int[][][][] ways;
//
//	{
//		for (int i = 11; i <= 11; i++) {
//			ways = new int[4][i][i][1 << i];
//			for (int j = 0; j < i; j++) {
//				for (int k = j + 1; k < i; k++)
//					ways[0][j][k][(1 << j) + (1 << k)] = 1;
//			}
//			for (int j = 0; j < 3; j++) {
//				for (int k = 0; k < i; k++) {
//					for (int l = k + 1; l < i; l++) {
//						for (int m = 0; m < (1 << i); m++) {
//							if (ways[j][k][l][m] == 0)
//								continue;
//							for (int n = 0; n < i; n++) {
//								if ((m >> n & 1) == 1)
//									continue;
//								for (int o = n + 1; o < i; o++) {
//									if ((m >> o & 1) == 1 || n + o > k + l || n + o == k + l && o > l)
//										continue;
//									ways[j + 1][k][l][m + (1 << n) + (1 << o)] += ways[j][k][l][m];
//								}
//							}
//						}
//					}
//				}
//			}
//			System.err.println("----------------");
//			System.err.println(i);
//			for (int l = 0; l < i; l++) {
//				for (int m = l + 1; m < i; m++) {
//					int total = (int) ArrayUtils.sumArray(ways[3][l][m]);
//					if (total != 0)
//						System.err.println(l + " " + m + " " + total / 6);
//				}
//			}
//		}
//
//	}

	long[][][] result;
	int small;
	int big;
	int count;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		result = new long[3][3][count];
		boolean[][] answer = new boolean[count][count];
		long threshold = calculateLess(3, count - 2) / 4;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				ArrayUtils.fill(result, -1);
				big = j;
				small = i;
				long total = calculateLess(3, i) + calculate(2, 0, (i + j) / 2);
//				if (total != 0 && testNumber == 4)
//					System.err.println(i + " " + j + " " + total);
				answer[i][j] = total > threshold;
			}
		}
		int handCount = in.readInt();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < handCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			if (first > second) {
				int temp = first;
				first = second;
				second = temp;
			}
			if (answer[first][second])
				result.append('B');
			else
				result.append('F');
		}
		out.printLine("Case #" + testNumber + ":", result);
	}

	long calculate(int remaining, int delta, int candidate) {
		if (candidate < 0 || remaining < 0)
			return 0;
		if (result[remaining][delta][candidate] != -1)
			return result[remaining][delta][candidate];
		result[remaining][delta][candidate] = calculate(remaining, delta, candidate - 1);
		if (candidate > small) {
			int max = Math.min(small + big - candidate, count - 1);
			int variants = Math.max(0, max - candidate - (2 - remaining) - delta);
			if (variants != 0)
				result[remaining][delta][candidate] += variants * (calculateLess(remaining, small) +
					calculate(remaining - 1, delta + 1, candidate - 1));
		}
		if (candidate < small) {
			int max = Math.min(small + big - candidate - 1, count - 1);
			int variants = Math.max(0, max - small - (2 - remaining) - 1 - delta);
			if (variants != 0)
				result[remaining][delta][candidate] += variants * (calculateLess(remaining, small - (2 - remaining - delta) - 1) +
					calculate(remaining - 1, delta, candidate - 1));
		}
		return result[remaining][delta][candidate];
	}

	private long calculateLess(int remaining, long variants) {
		if (variants < 0)
			return 0;
		if (remaining == 3)
			return variants * (variants - 1) * (variants - 2) * (variants - 3) * (variants - 4) * (variants - 5) / 48;
		if (remaining == 2)
			return variants * (variants - 1) * (variants - 2) * (variants - 3) / 8;
		if (remaining == 1)
			return variants * (variants - 1) / 2;
		if (remaining == 0)
			return 1;
		throw new RuntimeException();
	}
}
