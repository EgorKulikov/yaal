import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int size = in.readInt();
		int[] capacity = IOUtils.readIntArray(in, count);
		int result = 0;
		for (int i : capacity)
			result += Math.max(i % size, i - 3 * size);
		out.println(result);
	}
}

