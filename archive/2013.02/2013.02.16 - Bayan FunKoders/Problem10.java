package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Problem10 {
	boolean[] isPrime = IntegerUtils.generatePrimalityTable((1 << 15) + 1);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int number = in.readInt();
		if (number == 0)
			throw new UnknownError();
		int answer = 0;
		for (int i = 2; i * 2 <= number; i++) {
			if (isPrime[i] && isPrime[number - i])
				answer++;
		}
		out.printLine(answer);
    }
}
