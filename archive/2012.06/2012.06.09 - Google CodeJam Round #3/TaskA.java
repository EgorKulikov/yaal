package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Comparator;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		final int[] length = IOUtils.readIntArray(in, count);
		final int[] probabilityDie = IOUtils.readIntArray(in, count);
		Integer[] order = ArrayUtils.order(count, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				int ratio = length[o1] * probabilityDie[o2] - length[o2] * probabilityDie[o1];
				if (ratio == 0)
					return o1 - o2;
				return ratio;
			}
		});
		out.print("Case #" + testNumber + ": ");
		out.printLine(order);
	}
}
