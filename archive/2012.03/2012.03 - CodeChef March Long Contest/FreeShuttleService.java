package net.egork;

import net.egork.numbers.MultiplicativeFunction;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class FreeShuttleService {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(MultiplicativeFunction.PHI.calculate(count));
	}
}
