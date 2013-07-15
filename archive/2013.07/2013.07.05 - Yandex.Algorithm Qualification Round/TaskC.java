package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long count = in.readLong();
		long first = (count * (count + 1)) >> 1;
		long second;
		if ((count & 1) == 0)
			second = count - Long.highestOneBit(count) + 1;
		else if ((count & 3) == 3)
			second = 0;
		else
			second = (count + 1) >> 1;
		out.printLine(first, second);
    }
}
