package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;

import java.util.Arrays;
import java.util.Collections;

public class RollingDiceDivOne {
	public long mostLikely(int[] dice) {
		Collections.sort(Array.wrap(dice), new ReverseComparator<Integer>());
		long from = 1;
		long to = dice[0];
		for (int i = 1; i < dice.length; i++) {
			from += dice[i];
			to++;
			if (from > to) {
				long nextFrom = (from + to) / 2;
				long nextTo = (from + to + 1) / 2;
				from = nextFrom;
				to = nextTo;
			}
		}
		return from;
	}

	public static void main(String[] args) {
		RollingDiceDivOne solver = new RollingDiceDivOne();
		long[][] count = new long[6][251];
		count[0][0] = 1;
		for (int i = 1; i <= 50; i++) {
			Arrays.fill(count[1], 0);
			for (int a = 0; a <= 250; a++) {
				for (int b = 1; a + b <= 250 && b <= i; b++)
					count[1][a + b] += count[0][a];
			}
			for (int j = 1; j <= i; j++) {
				Arrays.fill(count[2], 0);
				for (int a = 0; a <= 250; a++) {
					for (int b = 1; a + b <= 250 && b <= j; b++)
						count[2][a + b] += count[1][a];
				}
				for (int k = 1; k <= j; k++) {
					Arrays.fill(count[3], 0);
					for (int a = 0; a <= 250; a++) {
						for (int b = 1; a + b <= 250 && b <= k; b++)
							count[3][a + b] += count[2][a];
					}
					for (int l = 1; l <= k; l++) {
						Arrays.fill(count[4], 0);
						for (int a = 0; a <= 250; a++) {
							for (int b = 1; a + b <= 250 && b <= l; b++)
								count[4][a + b] += count[3][a];
						}
						for (int m = 1; m <= l; m++) {
							Arrays.fill(count[5], 0);
							for (int a = 0; a <= 250; a++) {
								for (int b = 1; a + b <= 250 && b <= m; b++)
									count[5][a + b] += count[4][a];
							}
							if (solver.mostLikely(new int[]{i, j, k, l, m}) != ListUtils.maxIndex(Array.wrap(count[5])))
								throw new RuntimeException();
						}
					}
				}
			}
		}
	}
}

