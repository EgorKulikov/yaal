package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] first = new int[count];
		int[] second = new int[count];
		IOUtils.readIntArrays(in, first, second);
		int[] order = ArrayUtils.order(second);
		long minDiff = Long.MAX_VALUE;
		long sum = 0;
		long[] minLast = new long[count];
		minLast[count - 1] = first[order[count - 1]];
		for (int i = count - 2; i >= 0; i--)
			minLast[i] = Math.min(minLast[i + 1], first[order[i]]);
		for (int i = 0; i < count; i++) {
			minDiff = Math.min(minDiff, first[order[i]] - second[order[i]]);
			out.printLine(Math.min(sum + minLast[i], sum + second[order[i]] + minDiff));
			sum += second[order[i]];
		}
	}
}
