package on2011_10.on2011_9_28.taska;



import net.egork.io.IOUtils;
import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] share = IOUtils.readIntArray(in, 3);
		int undecided = in.readInt();
		Rational undecidedShare = Rational.ONE;
		for (int part : share)
			undecidedShare = undecidedShare.subtract(new Rational(1, part));
		if (undecidedShare.compareTo(Rational.ZERO) <= 0) {
			out.println(-1);
			return;
		}
		Rational result = undecidedShare.reverse().multiply(undecided);
		if (result.denominator != 1) {
			out.println(-1);
			return;
		}
		for (int part : share) {
			if (result.divide(part).denominator != 1) {
				out.println(-1);
				return;
			}
		}
		out.println(result.numerator);
	}
}
