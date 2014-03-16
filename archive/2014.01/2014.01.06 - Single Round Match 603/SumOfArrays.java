package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.numbers.FastFourierTransform;

public class SumOfArrays {
    public String findbestpair(int n, int[] Aseed, int[] Bseed) {
		long[] a = generate(n, Aseed);
		long[] b = generate(n, Bseed);
		long[] result = new long[200000];
		long[] aCur = new long[100000];
		long[] bCur = new long[100000];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 100000; j++) {
				aCur[j] = Math.min(1, a[j]);
				bCur[j] = Math.min(1, b[j]);
			}
			long[] c = FastFourierTransform.multiply(aCur, bCur);
			for (int j = 0; j < 200000; j++)
				result[j] += c[j];
			for (int j = 0; j < 100000; j++) {
				a[j] -= aCur[j];
				b[j] -= bCur[j];
			}
		}
		int[] nonZeroA = getNonZero(a);
		int[] nonZeroB = getNonZero(b);
		for (int i : nonZeroA) {
			for (int j : nonZeroB)
				result[i + j] += Math.min(a[i], b[j]);
		}
		int at = -1;
		int total = 0;
		for (int i = 199998; i >= 0; i--) {
			if (result[i] > total) {
				total = (int) result[i];
				at = i;
			}
		}
		return total + " " + at;
    }

	private int[] getNonZero(long[] array) {
		IntList result = new IntArrayList();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0)
				result.add(i);
		}
		return result.toArray();
	}

	private long[] generate(int n, int[] seed) {
		int[] result = new int[n];
		result[0] = seed[0];
		result[1] = seed[1];
		for (int i = 2; i < n; i++)
			result[i] = (int) (((long)result[i - 1] * seed[2] + (long)result[i - 2] * seed[3] + seed[4]) % seed[5]);
		long[] qty = new long[100000];
		for (int i : result)
			qty[i]++;
		return qty;
	}
}
