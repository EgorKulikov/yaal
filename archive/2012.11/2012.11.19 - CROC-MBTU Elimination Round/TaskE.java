package net.egork;

import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		char[] events = in.readString().toCharArray();
		int[] count = new int[events.length + 1];
		for (int i = 0; i < events.length; i++) {
			if (events[i] == '+')
				count[i + 1] = count[i] + 1;
			else
				count[i + 1] = count[i] - 1;
		}
		int max = CollectionUtils.maxElement(Array.wrap(count));
		int min = CollectionUtils.minElement(Array.wrap(count));
		out.printLine(max - min);
	}
}
