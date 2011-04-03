package net.egork.y2011.m4.d3.regionalcontestfrommexico;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class FinancialManagement implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double sum = 0;
		for (int i = 0; i < 12; i++)
			sum += in.readDouble();
		sum /= 12;
		String result = String.format("%.2f", sum);
		for (int i = 6; i < result.length(); i += 4)
			result = result.substring(0, result.length() - i) + "," + result.substring(result.length() - i);
		out.println(testNumber + " $" + result);
	}
}

