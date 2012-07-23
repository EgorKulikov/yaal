import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		Point[] vertices = new Point[3];
		for (int i = 0; i < 3; i++) {
			int x = in.readInt();
			int y = in.readInt();
			vertices[i] = new Point(x, y);
		}
		out.printf("%.1f\n", new Polygon(vertices).square());
	}
}

