package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int d = in.readInt();
		int count = in.readInt();
		int[] daysPerMonth = IOUtils.readIntArray(in, count);
		int answer = 0;
		for (int i = 0; i < count - 1; i++)
			answer += d - daysPerMonth[i];
		out.printLine(answer);
	}
}
