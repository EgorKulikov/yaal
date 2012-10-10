import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskI implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		if (size % 2 == 0 || size % 3 == 0) {
			out.println("No");
			return;
		}
		out.println("Yes");
		for (int i = 0; i < size; i++)
			out.print((2 * i % size + 1) + " ");
	}
}

