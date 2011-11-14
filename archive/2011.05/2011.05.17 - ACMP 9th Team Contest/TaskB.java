import net.egork.geometry.GeometryUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int r = in.readInt();
		int l = in.readInt();
		int d = in.readInt();
		double height;
		double width;
		double alpha = 2 * Math.PI * r / l;
		if (l >= 4 * r) {
			if (d >= l) {
				out.printf("%.10f\n", l * Math.sin(alpha));
				return;
			}
			double beta = Math.acos((double)d / l);
			out.printf("%.10f\n", l * Math.sin(alpha + beta));
		} else if (l >= 2 * r) {
			if (d + GeometryUtils.epsilon > l * (1 + Math.cos(Math.PI - alpha))) {
				out.println(l);
				return;
			}
			double beta = Math.acos((double)(d - l) / l);
			out.printf("%.10f\n", l * (1 + Math.sin(alpha + beta - Math.PI)));
		} else {
			if (d >= 2 * l)
				out.printf("%.10f\n", (1 + Math.cos(Math.PI - alpha / 2)) * l);
			else
				out.println(2 * l);
		}
	}
}

