package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		double a = in.readDouble();
		double b = in.readDouble();
		double c = in.readDouble();
		double d = in.readDouble();
		double e = in.readDouble();
		double f = in.readDouble();
		if (a == c)
			out.println("circle");
		else if (a > 0 && c > 0)
			out.println("ellipse");
		else if (a == 0 || c == 0)
			out.println("parabola");
		else
			out.println("hyperbola");
	}
}

