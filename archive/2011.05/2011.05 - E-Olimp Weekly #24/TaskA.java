import net.egork.utils.Exit;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		double width = in.readDouble();
		double height = in.readDouble();
		if (width == 0 && height == 0) {
			Exit.exit(in, out);
			return;
		}
		double firstDiameter = width / Math.PI;
		double firstHeight = height - firstDiameter;
		double secondDiameter = Math.min(width, height / (1 + Math.PI));
		double secondHeight = width;
		double answer = Math.max(firstDiameter * firstDiameter * firstHeight * Math.PI / 4,
			secondDiameter * secondDiameter * secondHeight * Math.PI / 4);
		out.printf("%.3f\n", answer);
	}
}

