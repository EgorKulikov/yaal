package net.egork;

public class YetAnotherANDProblem {
    public String test(int[] X, int K, int[] queries) {
		int count = X.length;
		int[] value = new int[1 << count];
		for (int i = 0; i < (1 << count); i++) {
			value[i] = -1;
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 1)
					value[i] &= X[j];
			}
		}
		int minBitCount;
		int maxBitCount;
		if (K == 1)
			minBitCount = maxBitCount = 2;
		else {
			minBitCount = 3;
			maxBitCount = (int) Math.min(1L << K, count);
		}
		StringBuilder answer = new StringBuilder();
		for (int i : queries) {
			boolean found = false;
			for (int j = 0; j < (1 << count); j++) {
				if (value[j] == i && Integer.bitCount(j) >= minBitCount && Integer.bitCount(j) <= maxBitCount) {
					found = true;
					break;
				}
			}
			if (found)
				answer.append('+');
			else
				answer.append('-');
		}
		return answer.toString();
    }
}
