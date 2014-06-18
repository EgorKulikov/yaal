package net.egork;

import java.util.Arrays;

public class BuildingHeights {
    public int minimum(int[] heights) {
		int answer = 0;
		Arrays.sort(heights);
		for (int i = 1; i <= heights.length; i++) {
			int current = 0;
			for (int j = 0; j < i - 1; j++)
				current += heights[j];
			int result = Integer.MAX_VALUE;
			for (int j = i - 1; j < heights.length; j++) {
				current += heights[j];
				result = Math.min(result, i * heights[j] - current);
				current -= heights[j - i + 1];
			}
			answer ^= result;
		}
		return answer;
    }
}
