package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

public class TaskN implements Solver {
	private BigInteger[] result;

	public TaskN() {
		result = new BigInteger[100001];
		Arrays.fill(result, BigInteger.ONE);
		BigInteger[] next = new BigInteger[100001];
		for (int s = 2; s <= 100000; s *= 2) {
			for (int i = 0; i <= 100000; i++) {
				next[i] = result[i];
				if (i >= s)
					next[i] = next[i].add(next[i - s]);
			}
			BigInteger[] temp = next;
			next = result;
			result = temp;
		}
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int k = in.readInt();
		long m = in.readLong();
		out.println("Case " + testNumber + ": " + result[k].mod(BigInteger.valueOf(m)));
	}
}

