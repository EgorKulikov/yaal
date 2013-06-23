package net.egork;

import java.util.Arrays;

public class AntlerSwapping {
    public int getmin(int[] antler1, int[] antler2, int capacity) {
		int count = antler1.length;
		int[] all = new int[2 * count];
		System.arraycopy(antler1, 0, all, 0, count);
		System.arraycopy(antler2, 0, all, count, count);
		Arrays.sort(all);
		for (int i = 0; i < 2 * count; i += 2) {
			if (all[i + 1] - all[i] > capacity)
				return -1;
		}
		boolean[] valid = new boolean[1 << count];
		int[] size = new int[1 << count];
		for (int i = 0; i < valid.length; i++) {
			int k = 0;
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 1) {
					all[k++] = antler1[j];
					all[k++] = antler2[j];
				}
			}
			Arrays.sort(all, 0, k);
			valid[i] = true;
			for (int j = 0; j < k; j += 2) {
				if (all[j + 1] - all[j] > capacity)
					valid[i] = false;
			}
			size[i] = Integer.bitCount(i) - 1;
		}
		int[] answer = new int[1 << count];
		for (int i = 1; i < answer.length; i++) {
			answer[i] = Integer.MAX_VALUE / 2;
			if (!valid[i])
				continue;
			for (int j = i; j != 0; j = (j - 1) & i) {
				if (valid[j])
					answer[i] = Math.min(answer[i], answer[i - j] + size[j]);
			}
		}
		return answer[answer.length - 1];
    }
}
