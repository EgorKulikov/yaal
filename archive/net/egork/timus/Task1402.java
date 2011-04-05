package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Task1402 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		BigInteger[] answer = new BigInteger[n + 1];
		answer[0] = BigInteger.ONE;
		for (int i = 1; i <= n; i++)
			answer[i] = answer[i - 1].multiply(BigInteger.valueOf(i)).add(BigInteger.ONE);
		out.println(answer[n].subtract(BigInteger.valueOf(n + 1)));
	}
}

