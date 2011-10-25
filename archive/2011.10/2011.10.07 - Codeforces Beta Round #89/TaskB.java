import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt() + 1;
		for (int i = 1; i < 2 * size; i++) {
			for (int j = 0; j < Math.abs(size - i); j++)
				out.print("  ");
			for (int j = 0; j < size - Math.abs(size - i) - 1; j++)
				out.print(j + " ");
			out.print(size - Math.abs(size - i) - 1);
			for (int j = size - Math.abs(size - i) - 1 - 1; j >= 0; j--)
				out.print(" " + j);
			out.println();
		}
	}
}

