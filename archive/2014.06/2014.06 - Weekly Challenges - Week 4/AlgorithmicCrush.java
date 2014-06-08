package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class AlgorithmicCrush {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		int[] toAdd = new int[queryCount];
		IOUtils.readIntArrays(in, from, to, toAdd);
		final int[] position = new int[2 * queryCount];
		final int[] delta = new int[2 * queryCount];
		for (int i = 0; i < queryCount; i++) {
			position[2 * i] = from[i];
			delta[2 * i] = toAdd[i];
			position[2 * i + 1] = to[i] + 1;
			delta[2 * i + 1] = -toAdd[i];
		}
		int[] order = ArrayUtils.createOrder(2 * queryCount);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (position[first] != position[second])
					return position[first] - position[second];
				return delta[first] - delta[second];
			}
		});
		long answer = 0;
		long current = 0;
		for (int i : order) {
			current += delta[i];
			answer = Math.max(answer, current);
		}
		out.printLine(answer);
    }
}
