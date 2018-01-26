package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		char[] seats = IOUtils.readCharArray(in, count);
		int cupHolderCount = count + 1 - CollectionUtils.count(Array.wrap(seats), 'L') / 2;
		out.printLine(Math.min(count, cupHolderCount));
	}
}
