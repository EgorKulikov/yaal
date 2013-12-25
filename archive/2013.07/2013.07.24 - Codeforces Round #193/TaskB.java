package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		int[] value = IOUtils.readIntArray(in, count);
		long[] sums = ArrayUtils.partialSums(value);
		int first = 0;
		int second = length;
		int bestFirst = 0;
		for (int i = 1; i <= count - 2 * length; i++) {
			if (sums[i + length] - sums[i] > sums[bestFirst + length] - sums[bestFirst])
				bestFirst = i;
			if (sums[i + 2 * length] - sums[i + length] + sums[bestFirst + length] - sums[bestFirst] >
				sums[first + length] - sums[first] + sums[second + length] - sums[second])
			{
				first = bestFirst;
				second = i + length;
			}
		}
		out.printLine(first + 1, second + 1);
	}
}
