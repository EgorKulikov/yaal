package net.egork.timus;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1118 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int from = in.readInt();
		int to = in.readInt();
		if (from == 1) {
			out.println(1);
			return;
		}
		int answer = -1;
		Rational triviality = Rational.MAX_VALUE;
		for (int i = to; i >= from; i--) {
			long sumDivisors = IntegerUtils.calculateSumDivisors(i);
			Rational currentTriviality = new Rational(sumDivisors, i);
			if (currentTriviality.compareTo(triviality) < 0) {
				triviality = currentTriviality;
				answer = i;
			}
			if (sumDivisors == i + 1)
				break;
		}
		out.println(answer);
	}
}

