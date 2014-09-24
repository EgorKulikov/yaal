package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class GardenGame {
	static final long MOD = (long) (1e9 + 7);

	int[] primes = IntegerUtils.generatePrimes(1000);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(permutation);
		int[] cycles = new int[count];
		for (int i = 0; i < count; i++) {
			if (cycles[i] == -1) {
				continue;
			}
			int length = 0;
			int current = i;
			do {
				length++;
				current = permutation[current];
				cycles[current] = -1;
			} while (current != i);
			cycles[i] = length;
		}
		Arrays.sort(cycles);
		cycles = ArrayUtils.unique(cycles);
		long answer = 1;
		for (int i : primes) {
			int max = 0;
			for (int j = 0; j < cycles.length; j++) {
				int current = 0;
				while (cycles[j] % i == 0) {
					cycles[j] /= i;
					current++;
				}
				max = Math.max(max, current);
			}
			for (int j = 0; j < max; j++) {
				answer *= i;
				answer %= MOD;
			}
		}
		Arrays.sort(cycles);
		cycles = ArrayUtils.unique(cycles);
		for (int i : cycles) {
			if (i != -1) {
				answer *= i;
				answer %= MOD;
			}
		}
		out.printLine(answer);
    }
}
