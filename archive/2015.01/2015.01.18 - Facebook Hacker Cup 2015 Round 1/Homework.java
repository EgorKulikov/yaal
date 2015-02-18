package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Homework {
	int[] primality = new int[(int) (1e7 + 1)];
	{
		int[] primes = IntegerUtils.generatePrimes(primality.length);
		for (int i : primes) for (int j = i; j < primality.length; j += i) primality[j]++;
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		int value = in.readInt();
		int answer = 0;
		for (int i = from; i <= to; i++) if (primality[i] == value) answer++;
		out.printLine("Case #" + testNumber + ":", answer);
    }
}
