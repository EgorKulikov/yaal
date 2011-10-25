import net.egork.geometry.GeometryUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		GeometryUtils.epsilon = 5e-8;
		long diameter = in.readInt();
		double sqrt3 = Math.sqrt(3);
		double x1 = -.5;
		double x2 = .5;
		double x3 = 1;
		double y1 = sqrt3 / 2;
		double y2 = sqrt3 / 2;
		double y3 = 0;
		int index = 0;
		while (inside(x1, y1, x2, y2, x3, y3, diameter)) {
			index++;
			y1 += sqrt3;
			y2 += sqrt3;
			y3 += sqrt3;
		}
		index--;
		y1 -= sqrt3;
		y2 -= sqrt3;
		y3 -= sqrt3;
		long result = 0;
		result += 2 * index + 1;
		int delta = 1;
		while (true) {
			x1 += 1.5;
			x2 += 1.5;
			x3 += 1.5;
			y1 += sqrt3 / 2;
			y2 += sqrt3 / 2;
			y3 += sqrt3 / 2;
			index += delta;
			delta = 1 - delta;
			while (!inside(x1, y1, x2, y2, x3, y3, diameter)) {
				index--;
				y1 -= sqrt3;
				y2 -= sqrt3;
				y3 -= sqrt3;
				if (y1 < 0) {
					out.println(result);
					return;
				}
			}
			result += 4 * index + 2 * delta;
		}
	}

	private boolean inside(double x1, double y1, double x2, double y2, double x3, double y3, long diameter) {
		return Math.hypot(x1, y1) < diameter + GeometryUtils.epsilon && Math.hypot(x2, y2) < diameter + GeometryUtils.epsilon && Math.hypot(x3, y3) < diameter + GeometryUtils.epsilon;
	}
}

