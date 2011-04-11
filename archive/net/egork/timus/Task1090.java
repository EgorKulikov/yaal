package net.egork.timus;

import net.egork.arrays.ArrayWrapper;
import net.egork.arrays.ArrayUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1090 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		long[] answer = new long[k];
		for (int i = 0; i < k; i++)
			answer[i] = ArrayUtils.countUnorderedPairs(ArrayWrapper.wrap(in.readIntArray(n)));
		out.println(ArrayUtils.maxIndex(ArrayWrapper.wrap(answer)) + 1);
	}
}

