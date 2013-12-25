package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndSegments {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int[] primes = IntegerUtils.generatePrimes(100);
		int[][] qty = new int[count + 1][primes.length];
		for (int i = 0; i < count; i++) {
			System.arraycopy(qty[i], 0, qty[i + 1], 0, primes.length);
			int copy = array[i];
			for (int j = 0; j < primes.length; j++) {
				while (copy % primes[j] == 0) {
					qty[i + 1][j]++;
					copy /= primes[j];
				}
			}
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt();
			int modulo = in.readInt();
			long answer = 1;
			for (int j = 0; j < primes.length; j++)
				answer = answer * IntegerUtils.power(primes[j], qty[to][j] - qty[from][j], modulo) % modulo;
			out.printLine(answer);
		}
    }
}
