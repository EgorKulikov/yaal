package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	long[] fibonacci = IntegerUtils.generateFibonacci(81, -1);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int index = in.readInt();
		if (index == 0)
			throw new UnknownError();
		out.printLine(fibonacci[index]);
	}
}
