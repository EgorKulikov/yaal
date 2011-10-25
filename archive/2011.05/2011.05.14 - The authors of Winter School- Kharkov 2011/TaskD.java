import net.egork.numbers.IntegerUtils;
import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		try {
			String x = in.readString();
			int a = x.length() == 1 ? 1 : Integer.parseInt(x.substring(0, x.length() - 1));
			in.readCharacter();
			String y = in.readString();
			int b = y.length() == 1 ? 1 : Integer.parseInt(y.substring(0, y.length() - 1));
			in.readCharacter();
			int c = in.readInt();
			if (c < 0) {
				out.println(0);
				return;
			}
			long gcd = IntegerUtils.gcd(a, b);
			if (c % gcd != 0) {
				out.println(0);
				return;
			}
			a /= gcd;
			b /= gcd;
			c /= gcd;
			long first = (c * BigInteger.valueOf(a).modInverse(BigInteger.valueOf(b)).longValue()) % b;
			if (a * first <= c)
				out.println(1);
			else
				out.println(0);
		} catch (InputMismatchException e) {
			Exit.exit(in, out);
		}
	}
}

