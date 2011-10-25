import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class JumpItUp implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		int third = in.readInt();
		int answer = Math.max(second - first - 1, third - second - 1);
		out.println(answer);
	}
}

