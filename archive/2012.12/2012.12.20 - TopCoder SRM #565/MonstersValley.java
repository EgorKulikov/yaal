package net.egork;

import java.util.Arrays;

public class MonstersValley {
	public int minimumPrice(long[] dread, int[] price) {
		int count = dread.length;
		long[] maxDread = new long[2 * count + 1];
		long[] next = new long[2 * count + 1];
		Arrays.fill(maxDread, -1);
		maxDread[0] = 0;
		for (int i = 0; i < count; i++) {
			Arrays.fill(next, -1);
			for (int j = 0; j <= 2 * i; j++) {
				if (maxDread[j] == -1)
					continue;
				if (maxDread[j] >= dread[i])
					next[j] = Math.max(next[j], maxDread[j]);
				next[j + price[i]] = Math.max(next[j + price[i]], maxDread[j] + dread[i]);
			}
			long[] temp = maxDread;
			maxDread = next;
			next = temp;
		}
		for (int i = 0; i <= 2 * count; i++) {
			if (maxDread[i] != -1)
				return i;
		}
		throw new RuntimeException();
	}
}
