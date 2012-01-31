package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] heights = IOUtils.readIntArray(in, count);
		int min = CollectionUtils.minElement(Array.wrap(heights));
		int max = CollectionUtils.maxElement(Array.wrap(heights));
		int firstMax = Array.wrap(heights).indexOf(max);
		int lastMin = Array.wrap(heights).lastIndexOf(min);
		int answer = firstMax + count - 1 - lastMin;
		if (firstMax > lastMin)
			answer--;
		out.printLine(answer);
	}
}
