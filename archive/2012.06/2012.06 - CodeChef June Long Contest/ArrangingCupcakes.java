package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ArrangingCupcakes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int answer = count - 1;
		for (int i = 2; i * i <= count; i++) {
			if (count % i == 0)
				answer = count / i - i;
		}
		out.printLine(answer);
	}
}
