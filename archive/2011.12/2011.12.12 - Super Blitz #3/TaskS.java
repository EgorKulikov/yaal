package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskS {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int numerator = in.readInt();
		int denominator = in.readInt();
		Rational rational = new Rational(numerator, denominator);
		out.printLine(rational.numerator, rational.denominator);
	}
}
