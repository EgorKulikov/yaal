package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CPR {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int testerCount = in.readInt();
		int taskCount = in.readInt();
		if (testerCount > taskCount) {
			out.printLine(0);
			return;
		}
		double answer = 1;
		for (int i = 0; i < testerCount; i++)
			answer *= (double)(taskCount - i) / taskCount;
		out.printLine(answer);
	}
}
