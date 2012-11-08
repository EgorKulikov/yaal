package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PackingCandies {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int capacity = in.readInt();
		int typeCount = in.readInt();
		int requestCount = in.readInt();
		for (int i = 0; i < requestCount; i++) {
			in.readInt();
			capacity -= in.readInt();
		}
		out.printLine(IntegerUtils.binomialCoefficient(capacity + typeCount - 1, typeCount - 1, (long)(1e9 + 7)));
	}
}
