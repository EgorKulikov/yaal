package net.egork;

import net.egork.collections.intcollection.IntArray;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheMinimumNumberOfMoves {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] wages = IOUtils.readIntArray(in, count);
		IntArray array = new IntArray(wages);
		long answer = array.sum() - count * array.min();
		out.printLine(answer);
	}
}
