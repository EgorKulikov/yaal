package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HumbertombMoralombsSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		if (number == 0)
			throw new UnknownError();
		out.printLine(Math.round(Math.ceil(Math.sqrt(number - 0.5))));
    }
}
