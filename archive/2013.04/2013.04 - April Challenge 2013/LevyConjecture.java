package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LevyConjecture {
	int[] answer = new int[10001];

	{
		int[] primes = IntegerUtils.generatePrimes(10000);
		for (int i : primes) {
			for (int j : primes) {
				if (i + 2 * j <= 10000)
					answer[i + 2 * j]++;
				else
					break;
			}
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		out.printLine(answer[in.readInt()]);
    }
}
