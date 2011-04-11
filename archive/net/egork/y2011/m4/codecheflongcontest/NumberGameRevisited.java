package net.egork.y2011.m4.codecheflongcontest;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class NumberGameRevisited implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt() - 1;
		if ((n & 3) == 0)
			out.println("ALICE");
		else
			out.println("BOB");
	}
}

