package on2013_06.on2013_06_20_9_Round_Spiral__Round_3__Step_11_.E__Point_in_polygon;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point point = Point.readPoint(in);
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++) {
			points[i] = Point.readPoint(in);
		}
		out.printLine(new Polygon(points).contains(point) ? "YES" : "NO");
	}
}
