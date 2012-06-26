import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int intersectionCount = in.readInt();
		int roadCount = in.readInt();
		int answer = 0;
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(intersectionCount);
		for (int i = 0; i < roadCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			if (!setSystem.join(from, to)) {
				answer *= 2;
				answer++;
				if (answer >= 1000000009)
					answer -= 1000000009;
			}
			out.println(answer);
		}
	}
}

