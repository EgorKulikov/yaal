package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JimAndTheOrders {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] at = new int[count];
		int[] process = new int[count];
		IOUtils.readIntArrays(in, at, process);
		final int[] ready = new int[count];
		for (int i = 0; i < count; i++) {
			ready[i] = at[i] + process[i];
		}
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			@Override
			public int compare(int first, int second) {
				if (ready[first] != ready[second]) {
					return ready[first] - ready[second];
				}
				return first - second;
			}
		});
		for (int i = 0; i < count; i++) {
			order[i]++;
		}
		out.printLine(order);
	}
}
