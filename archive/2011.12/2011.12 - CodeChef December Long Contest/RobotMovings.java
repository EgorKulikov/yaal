package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class RobotMovings {
	private static final int MODULE = 1000000007;
//	private long[][] c = IntegerUtils.generateBinomialCoefficients(5000, MODULE);
	private long[] factorial = new long[5001];
	private long[] reverseFactorial = new long[5001];

	{
		factorial[0] = 1;
		reverseFactorial[0] = 1;
		for (int i = 1; i <= 5000; i++) {
			factorial[i] = factorial[i - 1] * i % MODULE;
			reverseFactorial[i] = IntegerUtils.reverse(factorial[i], MODULE);
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int turns = in.readInt();
		if (size == 0 && turns == 0)
			throw new UnknownError();
		long answer;
		if (turns % 2 == 1)
			answer = c(size - 2, (turns - 1) / 2) * c(size - 2, (turns - 1) / 2);
		else
			answer = c(size - 2, turns / 2) * c(size - 2, (turns - 1) / 2);
		answer %= MODULE;
		answer *= 2;
		answer %= MODULE;
		out.printLine(answer);
	}

	private long c(int n, int m) {
		long result = factorial[n] * reverseFactorial[m] % MODULE * reverseFactorial[n - m] % MODULE;
		return result;
	}
}
