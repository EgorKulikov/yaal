package on2013_10.on2013_10_14____Execute_2013.Tree_Boundary;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TreeBoundary {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = Point.readPoint(in);
		double answer = Polygon.convexHull(points).perimeter();
		out.printFormat("%.2f\n", answer);
    }
}
