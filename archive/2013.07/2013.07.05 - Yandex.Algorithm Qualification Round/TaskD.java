package net.egork;

import net.egork.numbers.IntegerUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		if (b == 1) {
			out.printLine("infinity");
			return;
		}
		Rational[] answer = new Rational[a + 1];
		answer[0] = new Rational(1, b - 1);
		long[][] c = IntegerUtils.generateBinomialCoefficients(a + 1);
		for (int i = 1; i <= a; i++) {
			answer[i] = Rational.ONE;
			for (int j = 0; j < i; j++)
				answer[i] = answer[i].add(answer[j].multiply(c[i][j]));
			answer[i] = answer[i].multiply(answer[0]);
		}
		out.printLine(answer[a]);
    }
}
