package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;

public class CucumberWatering {
	long[][][] result;
	int[] position;
	private int[] x;
	private int k;
	private Integer[] order;

	public long theMin(int[] x, int K) {
		this.x = x;
		k = K;
		order = ListUtils.order(Array.wrap(x));
		position = new int[x.length];
		for (int i = 0; i < position.length; i++)
			position[order[i]] = i;
		result = new long[x.length + 1][x.length][K + 1];
		ArrayUtils.fill(result, -1);
		return go(0, 0, 0);
	}

	private long go(int at, int last, int taken) {
		if (result[at][last][taken] != -1)
			return result[at][last][taken];
		long answer = Long.MAX_VALUE / 2;
		if (at == x.length) {
			if (taken == 0)
				return result[at][last][taken] = answer;
			answer = 0;
			long left = x[order[last]];
			long right = (long) (3 * 1e9);
			for (int i = 1; i < x.length; i++) {
				if (x[i] > left && x[i] < right) {
					if (x[i - 1] > left && x[i - 1] < right)
						answer += Math.min(Math.abs(x[i] - x[i - 1]), Math.min(x[i] - left, right - x[i]) + Math.min(x[i - 1] - left, right - x[i - 1]));
					else
						answer += Math.min(x[i] - left, right - x[i]);
				} else if (x[i - 1] > left && x[i - 1] < right)
					answer += Math.min(x[i - 1] - left, right - x[i - 1]);
			}
			return result[at][last][taken] = answer;
		}
		answer = go(at + 1, last, taken);
		if (taken != k) {
			long candidate = go(at + 1, at, taken + 1);
			if (taken == 0) {
				long left = -(long) (3 * 1e9);
				long right = x[order[at]];
				for (int i = 1; i < x.length; i++) {
					if (x[i] > left && x[i] < right) {
						if (x[i - 1] > left && x[i - 1] < right)
							candidate += Math.min(Math.abs(x[i] - x[i - 1]), Math.min(x[i] - left, right - x[i]) + Math.min(x[i - 1] - left, right - x[i - 1]));
						else
							candidate += Math.min(x[i] - left, right - x[i]);
					} else if (x[i - 1] > left && x[i - 1] < right)
						candidate += Math.min(x[i - 1] - left, right - x[i - 1]);
				}
			} else {
				long left = x[order[last]];
				long right = x[order[at]];
				for (int i = 1; i < x.length; i++) {
					if (x[i] > left && x[i] < right) {
						if (x[i - 1] > left && x[i - 1] < right)
							candidate += Math.min(Math.abs(x[i] - x[i - 1]), Math.min(x[i] - left, right - x[i]) + Math.min(x[i - 1] - left, right - x[i - 1]));
						else
							candidate += Math.min(x[i] - left, right - x[i]);
					} else if (x[i - 1] > left && x[i - 1] < right)
						candidate += Math.min(x[i - 1] - left, right - x[i - 1]);
				}
			}
			answer = Math.min(answer, candidate);
		}
		if (answer < 0)
			throw new RuntimeException();
		return result[at][last][taken] = answer;
	}


}

