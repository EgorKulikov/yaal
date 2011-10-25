import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		long maxGamesToday = in.readLong();
		int percentToday = in.readInt();
		int percentTotal = in.readInt();
		out.print("Case #" + testNumber + ": ");
		if (percentTotal == 100 || percentTotal == 0) {
			if (percentToday == percentTotal)
				out.println("Possible");
			else
				out.println("Broken");
			return;
		}
		for (int i = 1; i <= 100 && i <= maxGamesToday; i++) {
			if (percentToday * i % 100 == 0) {
				out.println("Possible");
				return;
			}
		}
		out.println("Broken");
	}
}

