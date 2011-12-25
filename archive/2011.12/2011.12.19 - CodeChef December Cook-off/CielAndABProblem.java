package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CielAndABProblem {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int answer = a - b;
		if (answer % 10 != 9)
			answer++;
		else
			answer--;
		out.printLine(answer);
	}
}
