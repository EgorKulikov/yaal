package net.egork.y2011.m4.d9.hugeeasycontest;

import net.egork.numbers.Rational;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskU implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		in.readInt();
		int k = in.readInt();
		Rational answer = new Rational((1L << k) - (k + 1), 1L << k);
		out.println("Case #" + testNumber + ": " + answer);
	}
}

