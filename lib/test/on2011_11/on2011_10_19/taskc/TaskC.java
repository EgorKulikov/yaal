package on2011_11.on2011_10_19.taskc;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private int[] primes = IntegerUtils.generatePrimes(32000);
	private static final long MOD = 1000000000;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int firstCount = in.readInt();
		int[] first = IOUtils.readIntArray(in, firstCount);
		int secondCount = in.readInt();
		int[] second = IOUtils.readIntArray(in, secondCount);
		int[] countFirst = countDivisors(first);
		int[] countSecond = countDivisors(second);
		Arrays.sort(first);
		Arrays.sort(second);
		long result = 1;
		double doubleResult = 1;
		for (int i = 0; i < primes.length; i++) {
			for (int j = 0; j < Math.min(countFirst[i], countSecond[i]); j++) {
				result *= primes[i];
				result %= MOD;
				doubleResult *= primes[i];
			}
		}
		int firstIndex = 0;
		int secondIndex = 0;
		while (firstIndex < firstCount && secondIndex < secondCount) {
			if (first[firstIndex] == second[secondIndex]) {
				result *= first[firstIndex];
				result %= MOD;
				doubleResult *= first[firstIndex];
				firstIndex++;
				secondIndex++;
			} else if (first[firstIndex] < second[secondIndex])
				firstIndex++;
			else
				secondIndex++;
		}
		if (result == doubleResult)
			out.printLine(result);
		else
			out.printFormat("%09d\n", (int)result);
	}

	private int[] countDivisors(int[] numbers) {
		int[] count = new int[primes.length];
		for (int i1 = 0, numbersLength = numbers.length; i1 < numbersLength; i1++) {
			int i = numbers[i1];
			while ((i & 1) == 0) {
				count[0]++;
				i >>= 1;
			}
			for (int j = 1; j < primes.length; j++) {
				while (i % primes[j] == 0) {
					count[j]++;
					i /= primes[j];
				}
			}
			numbers[i1] = i;
		}
		return count;
	}
}
