package on2016_01.on2016_01_31_SNWS_2016__R4.A___Analysis;



import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int d = in.readInt();
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			points[i] = Point.readPoint(in);
		}
		for (Segment segment : new Polygon(points).sides()) {
			if (segment.distance(Point.ORIGIN) <= d + GeometryUtils.epsilon) {
				out.printLine("Long");
				return;
			}
		}
		out.printLine("Middle");
	}
}
