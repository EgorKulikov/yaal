package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class SixteenBricks {
    public int maximumSurface(int[] height) {
		Arrays.sort(height);
		return (int) (16 + ArrayUtils.sumArray(Arrays.copyOfRange(height, 8, 16)) * 4 -
			ArrayUtils.sumArray(Arrays.copyOfRange(height, 2, 6)) * 2 -
			ArrayUtils.sumArray(Arrays.copyOfRange(height, 0, 2)) * 4);
    }
}
