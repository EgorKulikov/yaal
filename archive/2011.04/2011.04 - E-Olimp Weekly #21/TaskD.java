package April2011.EOlimpWeekly21;

import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int length = in.readInt();
		int[] count = new int[101];
		for (int i = 0; i < length; i++)
			count[in.readInt()]++;
		boolean first = true;
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j < count[i]; j++) {
				if (first)
					first = false;
				else
					out.print(" ");
				out.print(i);
			}
		}
		out.println();
	}
}

