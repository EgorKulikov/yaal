import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class SumOfTokens {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int limit = in.readInt();
		long result = (long)limit * (limit + 1) / 2;
		out.println(result);
	}
}
