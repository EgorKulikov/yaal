import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.math.BigInteger;

public class Ngon implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		if ((n & (n - 1)) == 0 || BigInteger.valueOf(n).isProbablePrime(20)) {
			out.println("Second");
		} else {
			out.println("First");
		}
	}
}

