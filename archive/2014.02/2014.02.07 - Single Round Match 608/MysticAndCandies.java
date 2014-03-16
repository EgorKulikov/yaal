package net.egork;

import net.egork.misc.ArrayUtils;

public class MysticAndCandies {
    public int minBoxes(int C, int X, int[] low, int[] high) {
		int count = low.length;
		int totalLow = (int) ArrayUtils.sumArray(low);
		boolean[] used = new boolean[count];
		int totalHigh = (int) ArrayUtils.sumArray(high);
		int excess = C - totalLow;
		int[] lowOrder = ArrayUtils.order(low);
		int[] highOrder = ArrayUtils.order(high);
		for (int i = 1; i < count; i++) {
			int lowSum = 0;
			int highSum = 0;
			for (int j = 0; j < i; j++) {
				lowSum += low[lowOrder[j]];
				highSum += high[highOrder[j]];
			}
			int best = Math.min(lowSum + excess, highSum);
			if (X + best > C)
				return count - i + 1;
		}
		return 1;
    }
}
