package on2013_06.on2013_06_20_9_Round_Spiral__Round_3__Step_11_.D__Convex_polygon;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++) {
			points[i] = Point.readPoint(in);
		}
		out.printLine(Polygon.convexHull(points).vertices.length == count ? "YES" : "NO");
	}
}
