import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int mushroomCount = in.readInt();
		int first = mushroomCount * 13 / 24;
		int second = mushroomCount / 6;
		int third = mushroomCount * 7 / 24;
		out.println(first + " " + second + " " + third);
	}
}

