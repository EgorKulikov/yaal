package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HelpRagetti {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		long[] partialSums = ArrayUtils.partialSums(numbers);
		long max = 0;
		long delta = 0;
		for (long i : partialSums) {
			max = Math.max(max, i);
			delta = Math.max(delta, max - i);
		}
		out.printLine(partialSums[count] + 2 * delta);
	}
}
