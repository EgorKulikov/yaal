package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] first = IOUtils.readIntArray(in, count);
		int[] second = IOUtils.readIntArray(in, count);
		int index = 0;
		for (int i : second) {
			if (first[index] == i)
				index++;
		}
		out.printLine(count - index);
	}
}
