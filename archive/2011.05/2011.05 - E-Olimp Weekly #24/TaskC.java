import net.egork.geometry.Point;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		Point[] points = new Point[4];
		for (int i = 0; i < 4; i++) {
			double x = in.readDouble();
			double y = in.readDouble();
			points[i] = new Point(x, y);
		}
		out.printf("%.3f %.3f\n", (points[0].x + points[2].x) / 2, (points[0].y + points[2].y) / 2);
		out.printf("%.3f %.3f\n", points[0].distance(points[2]), points[1].distance(points[3]));
	}
}

