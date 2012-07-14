package April2011.UVaHugeEasyContestII;

import net.egork.numbers.Rational;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskU implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		in.readInt();
		int k = in.readInt();
		Rational answer = new Rational((1L << k) - (k + 1), 1L << k);
		out.println("Case #" + testNumber + ": " + answer);
	}
}

