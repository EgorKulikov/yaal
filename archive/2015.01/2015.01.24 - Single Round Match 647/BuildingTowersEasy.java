package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;

public class BuildingTowersEasy {
    public int maxHeight(int N, int[] x, int[] t) {
		int[] height = new int[N];
		int j = 0;
		int max = -1;
		MiscUtils.decreaseByOne(x);
		for (int i = 0; i < N; i++) {
			max++;
			if (j < x.length && x[j] == i) {
				max = Math.min(max, t[j++]);
			}
			height[i] = max;
		}
		max = height[N - 1] - 1;
		for (int i = N - 1; i >= 0; i--) {
			max++;
			max = Math.min(max, height[i]);
			height[i] = Math.min(height[i], max);
		}
		return ArrayUtils.maxElement(height);
    }
}
