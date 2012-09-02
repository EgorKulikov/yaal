package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;

public class TheBrickTowerMediumDivOne {
	public int[] find(final int[] heights) {
		int[] answer = new int[heights.length];
		int max = Integer.MAX_VALUE;
		boolean[] used = new boolean[heights.length];
		for (int i = 0; i < answer.length; i++) {
			int current = -1;
			for (int j = 0; j < answer.length; j++) {
				if (!used[j] && heights[j] <= max) {
					current = j;
					break;
				}
			}
			if (current == -1) {
				int[] remaining = new int[answer.length - i];
				int index = 0;
				for (int j = 0; j < answer.length; j++) {
					if (!used[j])
						remaining[index++] = j;
				}
				ArrayUtils.sort(remaining, new IntComparator() {
					public int compare(int first, int second) {
						if (heights[first] != heights[second])
							return heights[first] - heights[second];
						return first - second;
					}
				});
				System.arraycopy(remaining, 0,answer, i, answer.length - i);
				break;
			}
			max = heights[current];
			used[current] = true;
			answer[i] = current;
		}
		return answer;
	}
}
