package net.egork.timus;

import net.egork.arrays.Array;
import net.egork.arrays.ArrayUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1090 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int k = in.readInt();
		long[] answer = new long[k];
		for (int i = 0; i < k; i++)
			answer[i] = ArrayUtils.countUnorderedPairs(Array.create(in.readIntArray(n)));
		out.println(ArrayUtils.maxIndex(Array.create(answer)) + 1);
	}
}

