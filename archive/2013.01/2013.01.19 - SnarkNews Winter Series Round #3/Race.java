package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Race {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] delta = new int[count];
		final int[] speed = new int[count];
		int[] order = ArrayUtils.createOrder(count);
		IOUtils.readIntArrays(in, delta, speed);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (speed[first] != speed[second])
					return speed[first] - speed[second];
				return delta[first] - delta[second];
			}
		});
		int left = 0;
		int right = (int) 1e9 + 1;
		while (left < right) {
			long middle = (left + right) >> 1;
			long current = delta[order[0]] + speed[order[0]] * middle;
			boolean good = true;
			for (int i = 1; i < count; i++) {
				long next = delta[order[i]] + speed[order[i]] * middle;
				if (next <= current) {
					good = false;
					break;
				}
				current = next;
			}
			if (good)
				right = (int) middle;
			else
				left = (int) (middle + 1);
		}
		out.printLine(left);
	}
}
