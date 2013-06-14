package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] length = new int[count];
		final int[] percentage = new int[count];
		IOUtils.readIntArrays(in, length, percentage);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				return (100 - percentage[first]) * length[second] * percentage[second] - (100 - percentage[second]) * length[first] * percentage[first];
			}
		});
		double expectedLength = 0;
		double answer = 0;
		for (int i : order) {
			answer += length[i];
			answer += (100 - percentage[i]) * expectedLength / 100d;
			expectedLength += percentage[i] * length[i] / 100d;
		}
		out.printLine(answer);
    }
}
