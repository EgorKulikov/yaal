package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	int[] primes = IntegerUtils.generatePrimes(100000);
	int[] tens;
	int[] matrix;
	int[][] digit = new int[5][100000];

	int[] nextPrime = new int[100001];

	{
		tens = new int[6];
		tens[0] = 1;
		for (int i = 1; i <= 5; i++)
			tens[i] = tens[i - 1] * 10;
		int index = primes.length - 1;
		for (int i = 100000; i >= 2; i--) {
			if (primes[index] == i)
				index--;
			nextPrime[i] = index + 1;
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++)
				digit[i][j] = j/ tens[i] % 10;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int start = in.readInt();
//		int start = primes[primes.length - testNumber];
		matrix = new int[Integer.toString(start).length()];
		matrix[0] = start;
		out.printLine(go(1));
	}

	private int go(int step) {
		if (step == matrix.length)
			return 1;
		int current = 0;
		for (int i = 0; i < step; i++)
			current += digit[matrix.length - step - 1][matrix[i]] * tens[matrix.length - i - 1];
		int from = nextPrime[current];
		int to = nextPrime[current + tens[matrix.length - step]];
		int answer = 0;
		for (int i = from; i < to; i++) {
			matrix[step] = primes[i];
			answer += go(step + 1);
		}
		return answer;
	}
}
