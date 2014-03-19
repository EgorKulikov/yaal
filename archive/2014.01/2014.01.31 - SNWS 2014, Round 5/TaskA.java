package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] seconds = new int[count];
		final int[] centies = new int[count];
		int[] period = new int[count];
		IOUtils.readIntArrays(in, seconds, centies, period);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (seconds[first] != seconds[second])
					return seconds[first] - seconds[second];
				return centies[first] - centies[second];
			}
		});
		long total = 0;
		int minPeriod = ArrayUtils.minElement(period);
		for (int i = 0; i < count; i++)
			total += (period[i] - seconds[i]) * 100L - centies[i];
		long answer = total;
		long last = 0;
		for (int i : order) {
			if (seconds[i] >= minPeriod)
				break;
			long current = seconds[i] * 100L + centies[i];
			total += (current - last) * count;
			total -= period[i] * 100L;
			answer = Math.min(answer, total);
			last = current;
		}
		out.printLine(answer);
    }
}
