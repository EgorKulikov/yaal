package net.egork.timus;

import net.egork.arrays.ArrayWrapper;
import net.egork.arrays.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1071 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		for (int base = 2; base * base <= x; base++) {
			if (ArrayUtils.isSubSequence(ArrayWrapper.wrap(IntegerUtils.representationInBase(x, base)),
				ArrayWrapper.wrap(IntegerUtils.representationInBase(y, base))))
			{
				out.println(base);
				return;
			}
		}
		long bestBase = Long.MAX_VALUE;
		long left = y + 1;
		long right = x / y;
		while (left < right) {
			long candidate = (left + right) >> 1;
			if (x - candidate * y < candidate)
				right = candidate;
			else
				left = candidate + 1;
		}
		if (left == right)
			bestBase = left;
		long delta = x - y;
		for (long i = 1; i * i < x; i++) {
			if (delta % i == 0) {
				if (delta / i > y)
					bestBase = Math.min(bestBase, delta / i);
			}
		}
		if (bestBase == Long.MAX_VALUE)
			out.println("No solution");
		else
			out.println(bestBase);
	}
}

