package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class GoForWin {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int good = in.readInt();
		Rational answer = new Rational(good, count).add(new Rational(count - good, 2 * count));
		out.printLine(answer);
	}
}
