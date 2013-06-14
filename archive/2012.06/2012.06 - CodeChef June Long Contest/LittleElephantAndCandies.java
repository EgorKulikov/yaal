package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittleElephantAndCandies {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int candies = in.readInt();
		if (candies >= ArrayUtils.sumArray(IOUtils.readIntArray(in, count)))
			out.printLine("Yes");
		else
			out.printLine("No");
	}
}
