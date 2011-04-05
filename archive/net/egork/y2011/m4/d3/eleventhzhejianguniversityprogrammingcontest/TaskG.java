package net.egork.y2011.m4.d3.eleventhzhejianguniversityprogrammingcontest;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class TaskG implements Solver {
	private final boolean[] isPrime;

	public TaskG() {
		isPrime = IntegerUtils.generatePrimalityTable(40001);
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int fromX = in.readInt();
		int toX = in.readInt();
		int fromY = in.readInt();
		int toY = in.readInt();
		int primeCount = 0;
		for (int i = fromX; i <= toX; i++) {
			for (int j = fromY; j <= toY; j++) {
				if (i == 0) {
					if ((Math.abs(j) & 3) == 3 && isPrime[Math.abs(j)])
						primeCount++;
				} else if (j == 0) {
					if ((Math.abs(i) & 3) == 3 && isPrime[Math.abs(i)])
						primeCount++;
				} else {
					if (isPrime[i * i + j * j])
						primeCount++;
				}
			}
		}
		out.println(new Rational(primeCount, (toX - fromX + 1) * (toY - fromY + 1)));
	}
}

