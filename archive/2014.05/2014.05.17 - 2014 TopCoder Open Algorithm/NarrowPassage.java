package net.egork;

import net.egork.misc.ArrayUtils;

public class NarrowPassage {
    public int minDist(int L, int[] a, int[] b) {
		int[] firstOrder = ArrayUtils.order(a);
		int[] secondOrder = ArrayUtils.order(b);
		int[] firstPosition = ArrayUtils.reversePermutation(firstOrder);
		int[] secondPosition = ArrayUtils.reversePermutation(secondOrder);
		int count = a.length;
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= count; i++) {
			for (int j = i; j <= count; j++) {
				if (i != j && firstOrder[j - 1] != secondOrder[j - 1])
					break;
				boolean good = true;
				for (int k = 0; k < count; k++) {
					if (firstPosition[k] < i && secondPosition[k] >= j || firstPosition[k] >= j && secondPosition[k] < i) {
						good = false;
						break;
					}
				}
				if (!good)
					break;
				int candidate = 0;
				for (int k = 0; k < count; k++) {
					if (firstPosition[k] < i)
						candidate += a[k] + b[k];
					else if (firstPosition[k] >= j)
						candidate += 2 * L - a[k] - b[k];
					else
						candidate += Math.abs(a[k] - b[k]);
				}
				result = Math.min(result, candidate);
			}
		}
		for (int i = 0; i <= count; i++) {
			for (int j = 0; j <= count; j++) {
				int candidate = 0;
				for (int k = 0; k < count; k++) {
					if (firstPosition[k] < i) {
						if (secondPosition[k] < j)
							candidate += a[k] + b[k];
						else
							candidate += a[k] + 2 * L - b[k];
					} else {
						if (secondPosition[k] < j)
							candidate += 2 * L - a[k] + b[k];
						else
							candidate += 2 * L - a[k] - b[k];
					}
				}
				result = Math.min(result, candidate);
			}
		}
		return result;
    }
}
