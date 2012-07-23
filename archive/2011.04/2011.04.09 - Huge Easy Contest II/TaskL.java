package April2011.UVaHugeEasyContestII;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskL implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char[] sequence = in.readString().toCharArray();
		long result = 0;
		for (char token : sequence) {
			result <<= 2;
			if (token == 'C')
				result++;
			else if (token == 'G')
				result += 2;
			else if (token == 'T')
				result += 3;
		}
		out.println("Case " + testNumber + ": (" + sequence.length + ":" + result + ")");
	}
}

