package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int sum = 0;
		for (int i = 0; i < 3; i++)
			sum += in.readCharacter() - '0';
		out.printFormat("%.3f\n", Math.sqrt(sum));
	}
}
