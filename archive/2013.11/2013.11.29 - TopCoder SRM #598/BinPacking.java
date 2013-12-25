package net.egork;

import java.util.Arrays;

public class BinPacking {
    public int minBins(int[] item) {
		Arrays.sort(item);
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= item.length; i += 3) {
			if (i != 0 && item[i - 1] != 100)
				break;
			int start = i;
			int end = item.length - 1;
			int answer = i / 3;
			while (start < end) {
				if (item[start] + item[end] <= 300) {
					start++;
					end--;
					answer++;
				} else {
					end--;
					answer++;
				}
			}
			if (start == end)
				answer++;
			result = Math.min(result, answer);
		}
		return result;
    }
}
