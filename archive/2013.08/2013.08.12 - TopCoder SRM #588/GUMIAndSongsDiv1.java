package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class GUMIAndSongsDiv1 {
    public int maxSongs(int[] duration, int[] tone, int T) {
		ArrayUtils.orderBy(tone, duration);
		int count = duration.length;
		int answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i; j < count; j++) {
				int remaining = T - tone[j] + tone[i];
				int[] length = new int[j - i + 1];
				for (int k = i; k <= j; k++)
					length[k - i] = duration[k];
				Arrays.sort(length);
				for (int i1 = 0; i1 < length.length; i1++) {
					int k = length[i1];
					remaining -= k;
					if (remaining >= 0)
						answer = Math.max(answer, i1 + 1);
					else
						break;
				}
			}
		}
		return answer;
    }
}
