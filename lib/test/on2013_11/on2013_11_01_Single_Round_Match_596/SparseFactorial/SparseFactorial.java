package on2013_11.on2013_11_01_Single_Round_Match_596.SparseFactorial;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;

import java.util.Arrays;

public class SparseFactorial {
    public long getCount(long lo, long hi, long divisor) {
		long[] startingAt = getStartingAt(divisor);
		long answer = 0;
		for (int i = 0; i < startingAt.length; i++) {
			long aStartingAt = startingAt[i];
			long from = Math.max(aStartingAt, lo);
			if (from == Long.MAX_VALUE)
				continue;
			if (from % divisor <= i)
				from += i - from % divisor;
			else
				from += i - from % divisor + divisor;
			if (from > hi)
				continue;
			answer += (hi - from) / startingAt.length + 1;
		}
		return answer;
    }

	private long[] getStartingAt(long divisor) {
		for (long i = 2; i * i <= divisor; i++) {
			if (divisor % i == 0) {
				int exponent = 0;
				do {
					exponent++;
					divisor /= i;
				} while (divisor % i == 0);
				if (divisor == 1)
					return getSimple(i, exponent);
				long[] first = getStartingAt(divisor);
				long[] second = getSimple(i, exponent);
				return unite(first, second);
			}
		}
		return getSimple(divisor, 1);
	}

	private long[] getSimple(long divisor, int exponent) {
		if (exponent == 1) {
			long[] result = new long[(int) divisor];
			Arrays.fill(result, Long.MAX_VALUE);
			for (int i = 0; i < divisor; i++) {
				long from = (long)i * i + divisor;
				int at = (int) (from % divisor);
				result[at] = Math.min(result[at], from);
			}
			return result;
		}
		int total = 1;
		for (int i = 0; i < exponent; i++)
			total *= divisor;
		long[] squares = new long[total];
		for (int i = 0; i < total; i++)
			squares[i] = (long) i * i;
		long[] result = new long[total];
		Arrays.fill(result, Long.MAX_VALUE);
		IntList[] at = new IntList[(int) divisor];
		for (int i = 0; i < divisor; i++)
			at[i] = new IntArrayList();
		for (int j = 0; j < total; j++)
			at[((int) (squares[j] % divisor))].add(j);
		for (int i = 0; i < divisor; i++) {
			int[] current = at[i].toArray();
			if (current.length == 0)
				continue;
			for (int j = i; j < total; j += divisor) {
				int remaining = exponent;
				for (int k : current) {
					long delta = j - squares[k];
					if (delta <= 0) {
						delta %= total;
						delta += total;
					}
					long curAt = delta + squares[k];
					while (delta % divisor == 0) {
						delta /= divisor;
						remaining--;
					}
					if (remaining <= 0) {
						result[j] = curAt;
						break;
					}
				}
			}
		}
		return result;
	}

	private long[] unite(long[] first, long[] second) {
		long[] result = new long[first.length * second.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Math.max(first[i % first.length], second[i % second.length]);
			if (result[i] == Long.MAX_VALUE)
				continue;
			if (result[i] % result.length <= i)
				result[i] += i - result[i] % result.length;
			else
				result[i] += i - result[i] % result.length + result.length;
		}
		return result;
	}
}
