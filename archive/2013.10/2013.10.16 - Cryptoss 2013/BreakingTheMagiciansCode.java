package net.egork;

import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BreakingTheMagiciansCode {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		if (count == 0)
			throw new UnknownError();
		long answer = MiscUtils.josephProblem(count, 2);
		if (answer == 0)
			answer = count;
		out.printLine(answer);
    }
}
