package April2011.CodeforcesBetaRound67;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int a = in.readInt();
		int b = in.readInt();
		int c = a + b;
		a = Integer.parseInt(Integer.toString(a).replaceAll("0", ""));
		b = Integer.parseInt(Integer.toString(b).replaceAll("0", ""));
		c = Integer.parseInt(Integer.toString(c).replaceAll("0", ""));
		if (a + b == c)
			out.println("YES");
		else
			out.println("NO");
	}
}

