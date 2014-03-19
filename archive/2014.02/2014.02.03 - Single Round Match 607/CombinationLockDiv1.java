package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;

import java.util.Arrays;

public class CombinationLockDiv1 {
    public int minimumMoves(String[] P, String[] Q) {
		char[] start = StringUtils.unite(P).toCharArray();
		char[] end = StringUtils.unite(Q).toCharArray();
		int[] current = new int[start.length * 2 + 1];
		int inf = Integer.MAX_VALUE / 2;
		Arrays.fill(current, inf);
		int middle = start.length;
		current[middle] = 0;
		int[] next = new int[current.length];
		int last = 0;
		for (int i = 0; i < start.length; i++) {
			Arrays.fill(next, inf);
			int delta = (int)end[i] - start[i];
			if (delta < 0)
				delta += 10;
			if (delta >= last) {
				for (int j = 0; j < middle; j++) {
					if (current[j] != inf) {
						next[j] = Math.min(next[j], current[j]);
						if (j != 0)
							next[j - 1] = Math.min(next[j - 1], current[j] + last + 10 - delta);
					}
				}
				next[middle - 1] = Math.min(next[middle - 1], 10 - delta + current[middle]);
				next[middle] = Math.min(next[middle], current[middle] + delta - last);
				for (int j = middle + 1; j < current.length; j++) {
					if (current[j] != inf) {
						next[j] = Math.min(next[j], current[j] + delta - last);
						next[j - 1] = Math.min(next[j - 1], current[j]);
					}
				}
			} else {
				for (int j = 0; j < middle - 1; j++) {
					if (current[j] != inf) {
						next[j + 1] = Math.min(next[j + 1], current[j]);
						next[j] = Math.min(next[j], current[j] + last - delta);
					}
				}
				next[middle - 1] = Math.min(next[middle - 1], last - delta + current[middle - 1]);
				next[middle] = Math.min(next[middle], current[middle - 1] + delta);
				for (int j = middle; j < current.length; j++) {
					if (current[j] != inf) {
						next[j] = Math.min(next[j], current[j]);
						if (j + 1 < current.length)
							next[j + 1] = Math.min(next[j + 1], current[j] + 10 - last + delta);
					}
				}
			}
			int[] temp = current;
			current = next;
			next = temp;
			last = delta;
		}
		return ArrayUtils.minElement(current);
    }
}
