package on2015_12.on2015_12_09_Codeforces_Round__335__Div__1_.C___Freelancer_s_Dreams;



import net.egork.collections.set.EHashSet;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int p = in.readInt();
		int q = in.readInt();
		Point[] points = new Point[n + 3];
		double x = 0;
		double y = 0;
		for (int i = 0; i < n; i++) {
			points[i] = Point.readPoint(in);
			x = Math.max(x, points[i].x);
			y = Math.max(y, points[i].y);
		}
		points[n] = new Point(x, 0);
		points[n + 1] = new Point(0, y);
		points[n + 2] = Point.ORIGIN;
		points = new EHashSet<>(Arrays.asList(points)).toArray(new Point[0]);
		Segment[] sides = Polygon.convexHull(points).sides();
		Line line = Point.ORIGIN.line(new Point(p, q));
		double dst = 0;
		for (Segment segment : sides) {
			if (segment.line() == null) {
				continue;
			}
			Point point = segment.intersect(line);
			if (point == null) {
				continue;
			}
			dst = Math.max(dst, Point.ORIGIN.distance(point));
		}
		out.printLine(new Point(p, q).distance(Point.ORIGIN) / dst);
	}
}
