package net.egork.y2011.m4.d9.coci;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class Secer implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		try {
		int n = in.readInt();
		if (n % 5 == 0)
			out.println(n / 5);
		else if (n % 5 == 1 && n >= 6)
			out.println((n - 6) / 5 + 2);
		else if (n % 5 == 2 && n >= 12)
			out.println((n - 12) / 5 + 4);
		else if (n % 5 == 3 && n >= 3)
			out.println((n - 3) / 5 + 1);
		else if (n % 5 == 4 && n >= 9)
			out.println((n - 9) / 5 + 3);
		else
			out.println(-1);
		} catch (Throwable e) {}
	}
}

