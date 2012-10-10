package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskI {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		out.println(new Rational(second - 1, first + second - 2));
	}
}
