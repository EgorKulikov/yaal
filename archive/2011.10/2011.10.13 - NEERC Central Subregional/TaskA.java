import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] lengths = IOUtils.readIntArray(in, count);
		long g = 0;
		for (int length : lengths)
			g = IntegerUtils.gcd(g, length);
		long answer = 0;
		for (int length : lengths)
			answer += length / g;
		out.println(answer);
	}
}

