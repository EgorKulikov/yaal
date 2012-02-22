package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.collections.sequence.ListUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CountOfMaximum {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] qty = new int[10001];
		for (int i = 0; i < count; i++)
			qty[in.readInt()]++;
		out.printLine(ListUtils.maxIndex(Array.wrap(qty)), CollectionUtils.maxElement(Array.wrap(qty)));
	}
}
