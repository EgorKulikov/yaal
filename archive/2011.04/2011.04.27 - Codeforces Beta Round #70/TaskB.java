import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int eggCount = in.readInt();
		char[] result = new char[eggCount];
		char[] colors = {'R', 'O', 'Y', 'G', 'B', 'I', 'V'};
		for (int i = 0; i < eggCount - 3; i++)
			result[i] = colors[i & 3];
		for (int i = 1; i <= 3; i++)
			result[eggCount - i] = colors[7 - i];
		out.println(new String(result));
	}
}

