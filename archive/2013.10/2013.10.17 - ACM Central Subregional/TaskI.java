package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskI {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long rowCount = in.readInt();
		long columnCount = in.readInt();
		long hits = in.readLong();
		long misses = in.readLong();
		long min = (hits + 7) / 8;
		long max = Math.min((rowCount / 3) * (columnCount / 3), (rowCount * columnCount - misses) / 8);
		if (min <= max)
			out.printLine(min, max);
		else
			out.printLine("BAZINGA!");
    }
}
