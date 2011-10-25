import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskG implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int number = in.readInt();
		while ((number & (1 << 31)) == 0)
			number <<= 1;
		out.println(Integer.reverse(number));
	}
}

