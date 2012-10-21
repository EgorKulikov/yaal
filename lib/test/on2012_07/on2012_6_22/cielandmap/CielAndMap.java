package on2012_07.on2012_6_22.cielandmap;



import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CielAndMap {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		if (count > 4) {
			int maxDistance = 0;
			for (int i = 0; i < count; i++) {
				for (int j = i + 1; j < count; j++)
					maxDistance = Math.max(maxDistance, (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
			}
			out.printLine(Math.sqrt(maxDistance));
			return;
		}
		Point[] points = new Point[4];
		for (int i = 0; i < 4; i++)
			points[i] = new Point(x[i], y[i]);
		double result = 0;
		Segment[] segments = new Segment[4];
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (i == j)
					continue;
				int k = 6 - i - j;
				segments[0] = new Segment(points[0], points[i]);
				segments[1] = new Segment(points[j], points[i]);
				segments[2] = new Segment(points[j], points[k]);
				segments[3] = new Segment(points[0], points[k]);
				if (segments[0].intersect(segments[2], true) == null && segments[1].intersect(segments[3], true) == null) {
					for (Segment segment : segments)
						result = Math.max(result, segment.length());
				}
			}
		}
		out.printLine(result);
	}
}
