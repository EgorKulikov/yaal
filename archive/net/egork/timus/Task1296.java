package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Task1296 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[] p = in.readIntArray(n);
		long answer = 0;
		NavigableSet<Long> sums = new TreeSet<Long>();
		long sum = 0;
		for (int pp : p) {
			sums.add(-sum);
			sum += pp;
			answer = Math.max(answer, sums.last() + sum);
		}
		out.println(answer);
	}
}

