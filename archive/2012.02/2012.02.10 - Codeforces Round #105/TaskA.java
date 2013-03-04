package net.egork;

import net.egork.collections.comparators.ReverseComparator;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Collections;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int minDelta = in.readInt();
		int[] delta = IOUtils.readIntArray(in, 12);
		Collections.sort(Array.wrap(delta), new ReverseComparator<Integer>());
		for (int i = 0; i < 12; i++) {
			if (minDelta <= 0) {
				out.printLine(i);
				return;
			}
			minDelta -= delta[i];
		}
		if (minDelta <= 0)
			out.printLine(12);
		else
			out.printLine(-1);
	}
}
