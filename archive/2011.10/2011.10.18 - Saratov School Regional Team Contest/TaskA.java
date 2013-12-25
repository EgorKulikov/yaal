import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String type = in.readString();
		int index = in.readInt();
		boolean left = "front".equals(type) ^ (index == 2);
		if (left)
			out.println("L");
		else
			out.println("R");
	}
}

