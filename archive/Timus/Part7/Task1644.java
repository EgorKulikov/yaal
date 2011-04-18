package Timus.Part7;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1644 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int experimentCount = in.readInt();
		int required = 10;
		int notEnough = 2;
		for (int i = 0; i < experimentCount; i++) {
			int count = in.readInt();
			boolean satisfied = in.readString().equals("satisfied");
			if (satisfied)
				required = Math.min(required,  count);
			else
				notEnough = Math.max(notEnough, count);
		}
		if (notEnough < required)
			out.println(required);
		else
			out.println("Inconsistent");
	}
}

