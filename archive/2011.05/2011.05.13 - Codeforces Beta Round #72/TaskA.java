import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int size = in.readInt();
		int last = Integer.MAX_VALUE;
		long answer = 0;
		long count = 0;
		for (int i = 0; i < size; i++) {
			int value = in.readInt();
			if (value == last)
				count++;
			else {
				answer += count * (count + 1) / 2;
				count = 1;
				last = value;
			}
		}
		answer += count * (count + 1) / 2;
		out.println(answer);
	}
}

