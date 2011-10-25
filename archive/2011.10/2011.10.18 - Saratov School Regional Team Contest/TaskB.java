import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int index = in.readInt() - 1;
		int[] taken = IOUtils.readIntArray(in, count);
		for (int i = index; ; i++) {
			if (taken[i % count] == 1) {
				out.println(i % count + 1);
				return;
			}
		}
	}
}

