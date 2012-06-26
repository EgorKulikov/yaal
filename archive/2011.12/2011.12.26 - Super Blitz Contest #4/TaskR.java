package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskR {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		long answer = 0;
		for (int i = 0; i < count; i++)
			answer = IntegerUtils.gcd(answer, in.readLong());
		out.printLine(answer);
	}
}
