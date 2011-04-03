package net.egork.y2011.m4.d3;

import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.io.stringinputreader.StringInputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CalculatingIRR implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		in = new StringInputReader(in.readLine());
		double initialInvestment = in.readDouble();
		List<Double> earnings = new ArrayList<Double>();
		try {
			//noinspection InfiniteLoopStatement
			while (true)
				earnings.add(in.readDouble());
		} catch (InputMismatchException ignored) {
		}
		double left = 0;
		double right = 200;
		while (right - left > 1e-5) {
			double profit = 0;
			double percent = (left + right) / 2;
			double percentPower = percent / 100;
			for (double earning : earnings) {
				profit += earning / percentPower;
				percentPower *= percent / 100;
			}
			if (profit > initialInvestment)
				left = percent;
			else
				right = percent;
		}
		out.printf("%.3f", (left + right) / 2 - 100);
		out.println("%");
	}
}

