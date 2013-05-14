package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ClimbingStairs {
	long[] fibonacci = IntegerUtils.generateFibonacci(1000001, 1000000007);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int steps = in.readInt();
		int answer = in.readInt();
		if (Long.bitCount(fibonacci[steps]) == answer)
			out.printLine("CORRECT");
		else
			out.printLine("INCORRECT");
    }
}
