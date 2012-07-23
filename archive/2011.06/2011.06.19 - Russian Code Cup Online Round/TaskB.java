import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int letterCount = in.readInt();
		int[] recipient = IOUtils.readIntArray(in, letterCount);
		int down = 0;
		int up = 0;
		for (int i = 0; i < letterCount; i++) {
			int current = recipient[i] - i - 1;
			if (current > 0)
				up = Math.max(current, up);
			else
				down = Math.max(-current, down);
		}
		out.println(up + down + Math.min(up, down));
	}
}

