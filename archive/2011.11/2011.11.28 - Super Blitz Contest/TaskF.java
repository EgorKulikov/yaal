package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] numbers = IOUtils.readIntArray(in, 3);
		boolean odd = false;
		boolean even = false;
		for (int i : numbers) {
			if (i % 2 == 0)
				even = true;
			else
				odd = true;
		}
		if (even && odd)
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
