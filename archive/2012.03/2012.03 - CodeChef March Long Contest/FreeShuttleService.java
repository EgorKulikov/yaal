package net.egork;

import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FreeShuttleService {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(MultiplicativeFunction.PHI.calculate(count));
	}
}
