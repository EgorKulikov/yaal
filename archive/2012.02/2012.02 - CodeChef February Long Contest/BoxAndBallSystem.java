package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class BoxAndBallSystem {
	final int[] answer;

	private static final long MOD = 5 * (int)1e5 + 9;

	{
		answer = IntegerUtils.generateDivisorTable(3000000);
		for (int i = 2; i < answer.length; i++) {
			int number = i;
			int multiply = 1;
			do {
				multiply++;
				number /= answer[i];
			} while (number % answer[i] == 0);
			answer[i] = answer[number] * multiply;
		}
		long[] count = new long[433];
		answer[1] = 1;
		for (int i = 2; i < 3000000; i++)
			answer[i] = (int) (answer[i - 1] * ++count[answer[i]] % MOD);
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		if (n >= answer.length)
			out.printLine(MOD - 1);
		else
			out.printLine((MOD - 1 + answer[n]) % MOD);
	}
}
