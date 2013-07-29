package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MathematicianAna {
	long[] answer = new long[51];

	{
		answer[1] = 1;
		boolean[] isPrime = IntegerUtils.generatePrimalityTable(51);
		for (int i = 2; i <= 50; i++) {
			answer[i] = answer[i - 1];
			if (isPrime[i])
				answer[i] *= i;
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		if (number == 0)
			throw new UnknownError();
		out.printLine(answer[number]);
    }
}
