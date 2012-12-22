package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long[] divisorCount = MultiplicativeFunction.DIVISOR_COUNT.calculateUpTo(10000);
		out.printLine(Array.wrap(divisorCount).indexOf((long)count));
	}
}
