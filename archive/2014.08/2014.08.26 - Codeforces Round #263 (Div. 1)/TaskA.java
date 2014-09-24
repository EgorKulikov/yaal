package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		ArrayUtils.sort(array, IntComparator.DEFAULT);
		long answer = 0;
		for (int i = 0; i < count; i++) {
			answer += (long)Math.min(i + 2, count) * array[i];
		}
		out.printLine(answer);
	}
}
