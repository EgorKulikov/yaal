package on2015_10.on2015_10_31_Yekaterinbirg_GP.TaskE;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.geometry.Circle;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Map<IntIntPair, Point> points = new HashMap<>();
		Point[][] triangles = new Point[n][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				int x = in.readInt();
				int y = in.readInt();
				Point p = new Point(x, y);
				points.put(new IntIntPair(x, y), p);
				triangles[i][j] = p;
			}
		}
		for (int i = 0; i < n; i++) {
			Line first = triangles[i][0].line(triangles[i][1]).perpendicular(new Segment(triangles[i][0], triangles[i][1]).middle());
			Line second = triangles[i][0].line(triangles[i][2]).perpendicular(new Segment(triangles[i][0], triangles[i][2]).middle());
			Point center = first.intersect(second);
			Circle circle = new Circle(center, center.distance(triangles[i][0]));
			for (Point point : points.values()) {
				if (center.distance(point) < circle.radius - 1e-8) {
					out.printLine("NO");
					return;
				}
			}
		}
		out.printLine("YES");
	}
}
