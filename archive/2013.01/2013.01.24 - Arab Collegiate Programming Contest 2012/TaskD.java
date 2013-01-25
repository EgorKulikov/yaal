package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] candies = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i : candies)
			answer += i / required;
		out.printLine(answer);
	}
}
