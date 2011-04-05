package net.egork.timus;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Task1206 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int k = in.readInt();
		out.println(BigInteger.valueOf(36).multiply(BigInteger.valueOf(55).pow(k - 1)));
	}
}

