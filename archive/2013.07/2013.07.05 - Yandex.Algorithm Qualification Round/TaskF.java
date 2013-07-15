package net.egork;

import net.egork.collections.map.Counter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if ((count & (count - 1)) != 0) {
			out.printLine("NO");
			return;
		}
		long[] qty = IOUtils.readLongArray(in, count);
		Counter<Long> counter = new Counter<Long>();
		for (long i : qty)
			counter.add(i);
		long[] remaining = new long[counter.size()];
		int j = 0;
		for (long i : counter.values())
			remaining[j++] = i;
		long target = count / 2;
		while (target != 0) {
			Arrays.sort(remaining);
			if (remaining[remaining.length - 1] < target) {
				out.printLine("NO");
				return;
			}
			remaining[remaining.length - 1] -= target;
			target /= 2;
		}
		out.printLine("YES");
    }
}
