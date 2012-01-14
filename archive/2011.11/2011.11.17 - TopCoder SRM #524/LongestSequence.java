package net.egork;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class LongestSequence {
	public int maxLength(int[] C) {
		boolean hasPositive = false;
		boolean hasNegative = false;
		for (int i : C) {
			if (i < 0)
				hasNegative = true;
			else
				hasPositive = true;
		}
		if (!hasPositive || !hasNegative)
			return -1;
//		long gcd = 0;
//		for (int i : C)
//			gcd = IntegerUtils.gcd(gcd, Math.abs(i));
//		if (gcd != 1) {
//			for (int i = 0; i < C.length; i++)
//				C[i] /= gcd;
//			return (int) ((maxLength(C) + 1) * gcd - 1);
//		}
		int[] shift = new int[2001];
		Arrays.fill(shift, Integer.MAX_VALUE);
		Queue<Integer> queue = new ArrayBlockingQueue<Integer>(2001);
		boolean[] inQueue = new boolean[2001];
		for (int i : C) {
			shift[i + 1000] = 0;
			inQueue[i + 1000] = true;
			queue.add(i);
		}
		while (!queue.isEmpty()) {
			int current = queue.poll();
			inQueue[current + 1000] = false;
			if (current < 0) {
				for (int i = 1; i <= 1000; i++) {
					if (shift[i + 1000] == Integer.MAX_VALUE)
						continue;
					int newShift;
					if (current + i > 0)
						newShift = Math.max(shift[current + 1000], shift[i + 1000]) - current;
					else
						newShift = Math.max(shift[current + 1000], shift[i + 1000]) + i;
					if (shift[current + i + 1000] > newShift) {
						shift[current + i + 1000] = newShift;
						if (!inQueue[current + i + 1000]) {
							queue.add(current + i);
							inQueue[current + i + 1000] = true;
						}
					}
				}
			} else {
				for (int i = -1; i >= -1000; i--) {
					if (shift[i + 1000] == Integer.MAX_VALUE)
						continue;
					int newShift;
					if (current + i > 0)
						newShift = Math.max(shift[current + 1000], shift[i + 1000]) - i;
					else
						newShift = Math.max(shift[current + 1000], shift[i + 1000]) + current;
					if (shift[current + i + 1000] > newShift) {
						shift[current + i + 1000] = newShift;
						if (!inQueue[current + i + 1000]) {
							queue.add(current + i);
							inQueue[current + i + 1000] = true;
						}
					}
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= 1000; i++) {
			if (shift[1000 + i] != Integer.MAX_VALUE && shift[1000 - i] != Integer.MAX_VALUE)
				answer = Math.min(answer, Math.max(shift[1000 + i], shift[1000 - i]) + i - 1);
		}
		return answer;
	}

}

