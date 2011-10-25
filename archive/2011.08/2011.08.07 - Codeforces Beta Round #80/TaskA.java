import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		long[] variants = IOUtils.readLongArray(in, count);
		long answer = 0;
		for (int i = 0; i < variants.length; i++)
			answer += 1 + (i + 1) * (variants[i] - 1);
		out.println(answer);
	}
}

