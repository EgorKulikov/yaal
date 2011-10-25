import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int safeCount = in.readInt();
		int swapCount = in.readInt();
		int time = in.readInt();
		int[] diamonds = IOUtils.readIntArray(in, safeCount);
		if (safeCount % 2 == 0) {
			out.println(0);
			return;
		}
		int maximum = Integer.MAX_VALUE;
		for (int i = 0; i < safeCount; i += 2)
			maximum = Math.min(maximum, diamonds[i]);
		int required = (safeCount + 1) / 2;
		long answer = Math.min(maximum, ((long)swapCount / required) * time);
		out.println(answer);
	}
}

