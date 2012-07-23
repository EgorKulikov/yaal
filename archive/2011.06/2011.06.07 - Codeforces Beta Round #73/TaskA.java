import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long a = in.readInt();
		long b = in.readInt();
		long g = IntegerUtils.gcd(a, b);
		a /= g;
		b /= g;
		if (Math.abs(a - b) <= 1)
			out.println("Equal");
		else if (a < b)
			out.println("Dasha");
		else
			out.println("Masha");
	}
}

