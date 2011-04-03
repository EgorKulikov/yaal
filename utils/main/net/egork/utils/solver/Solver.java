package net.egork.utils.solver;

import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public interface Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out);
}
