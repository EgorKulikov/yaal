import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		boolean[] reachable = new boolean[200002];
		reachable[100000] = true;
		for (int number : numbers) {
			if (number > 0) {
				for (int i = 200000 - number; i >= 0; i--) {
					if (reachable[i])
						reachable[i + number] = true;
				}
			} else if (number < 0) {
				for (int i = -number; i <= 200000; i++) {
					if (reachable[i])
						reachable[i + number] = true;
				}
			}
		}
		for (int i = 1; ; i++) {
			if (!reachable[100000 + i]) {
				out.println(i);
				return;
			}
		}
	}
}

