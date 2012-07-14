package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long index = in.readInt();
		long row = func(index);
		out.printLine(row, row + index);
	}

	private long func(long count) {
		long total = 5 * count * count;
		long approximate = Math.max((long) (Math.sqrt(total) - 2), 0);
		while (approximate * approximate <= total)
			approximate++;
		approximate--;
		return (approximate + count) / 2;
	}
}
