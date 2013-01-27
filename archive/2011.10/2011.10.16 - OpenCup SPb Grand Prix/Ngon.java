import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Ngon implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int n = in.readInt();
		if ((n & (n - 1)) == 0 || BigInteger.valueOf(n).isProbablePrime(20)) {
			out.println("Second");
		} else {
			out.println("First");
		}
	}
}

