package net.egork.y2011.m4.d2;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TheLazyLumberjacks implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long a = in.readInt();
		long b = in.readInt();
		long c = in.readInt();
		if (a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a)
			out.println("OK");
		else
			out.println("Wrong!!");
	}
}

