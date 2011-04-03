package net.egork.y2011.m4.d3;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class ToWin implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double initialBet = in.readInt();
		int wagerCount = in.readInt();
		for (int i = 0; i < wagerCount; i++) {
			int payLine = in.readInt();
			double multiplier;
			if (payLine > 0)
				multiplier = payLine / 100.;
			else
				multiplier = -100. / payLine;
			multiplier = Double.parseDouble(String.format("%.3f", multiplier));
			initialBet *= 1 + multiplier;
			initialBet = Math.ceil(initialBet);
			initialBet = Math.min(initialBet, 1000000);
		}
		String result = String.format("%.2f", initialBet);
		for (int i = 6; i < result.length(); i += 4)
			result = result.substring(0, result.length() - i) + "," + result.substring(result.length() - i);
		out.println(testNumber + " $" + result);
	}
}

