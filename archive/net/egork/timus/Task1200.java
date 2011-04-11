package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1200 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double firstProfit = in.readDouble();
		double secondProfit = in.readDouble();
		int totalMax = in.readInt();
		int firstCount = 0;
		int secondCount = 0;
		double profit = 0;
		for (int i = 0; i < totalMax; i++) {
			double currentFirstProfit = firstProfit - 2 * firstCount - 1;
			double currentSecondProfit = secondProfit - 2 * secondCount - 1;
			if (Math.max(currentFirstProfit, currentSecondProfit) < 1e-5)
				break;
			if (currentFirstProfit < currentSecondProfit + 1e-5) {
				profit += currentSecondProfit;
				secondCount++;
			} else {
				profit += currentFirstProfit;
				firstCount++;
			}
		}
		out.printf("%.2f\n", profit);
		out.println(firstCount + " " + secondCount);
	}
}

