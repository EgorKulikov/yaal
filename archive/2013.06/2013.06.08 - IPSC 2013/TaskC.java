package net.egork;

import net.egork.collections.intcollection.IntArray;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.numbers.MultiplicativeFunction;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	long t(long x) {
		return Math.min(x, MultiplicativeFunction.PHI.calculate(x) + 1);
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		IntList A = new IntArrayList(new IntArray(new int[]{1894607624, 1927505134, 1861486949, 2052221263, 1953703270, 1772249212, 1704106159, 147456, 1829198849}));

		A.inPlaceSort();
		for (int i = 0; i < 4; ++i)
			A.set(i + 5, (int) (A.get(i + 5) ^ MultiplicativeFunction.PHI.calculate(A.get(i + 1) - A.get(0)) >> 7));
		long z = Math.max(t(A.get(0)) - 1, A.get(0) + 1 - t(A.get(0)));
//		out.printLine(z);
		int zSmall = (int) (z % A.size());
		int shift = 0;
		for (long i = 0; i < zSmall; ++i) {
			int first = A.get(shift);
			shift++;
			A.add(first);
		}
		A.add(A.back());
		for (int i = A.size() - 2; i > shift + 1; i--)
			A.set(i, A.get(i - 1));
		A.set(shift + 1, (int) z);
//		for (int i = shift; i < A.size(); i++) {
//			if (A.get(i) == 2052933809)
//				A.set(i, 853225919);
//		}
		for (long x = 8; x < 1e7; ++x) {
			int y = A.get(shift + (int) (x / 4)) >> (18 - 6 * (x % 4)) & 63;
			if (y == 0) break;
			if (y < 60)
				out.print(Character.toUpperCase((char)(31 + y)));
			else
				out.print(A.get(y - 60 + shift));
		}
		out.printLine();
	}
}
