package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndFunctions {
	private static final long MOD = (long) (1e9 + 7);
	long[] max = new long[60];
	
	{
		max[0] = IntegerUtils.power(10, 18);
		for (int i = 1; i < 60; i++)
			max[i] = Math.round(Math.floor(Math.pow(max[0], 1d / (i + 1)) + 1e-9));
	}
	
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		long tail = 0;
		for (int i = 60; i < count; i++)
			tail += array[i];
		tail %= MOD;
		long[] queries = IOUtils.readLongArray(in, queryCount);
		long[] answer = new long[queryCount];
		for (int i = 0; i < queryCount; i++) {
			long x = queries[i];
			for (int j = 0; j < 60 && j < count; j++) {
				long base;
				if (j == 0)
					base = x % MOD;
				else {
					long left = 1;
					long right = max[j];
					while (left < right) {
						long middle = (left + right + 1) >> 1;
						if (IntegerUtils.power(middle, j + 1) <= x)
							left = middle;
						else
							right = middle - 1;
					}
					base = left;
				}
				answer[i] += base * array[j] % MOD;
			}
			answer[i] += tail;
			answer[i] %= MOD;
			if (answer[i] < 0)
				answer[i] += MOD;
		}
		out.printLine(answer);
    }
}
