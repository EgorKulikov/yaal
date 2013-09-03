package on2013_08.on2013_08_30_SnarkNews_Summer_Series_Round__5.TaskC;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point[] points = new Point[4];
		for (int i = 0; i < 4; i++)
			points[i] = Point.readPoint(in);
		double total = new Polygon(points).square();
		if (total == 0)
			throw new UnknownError();
		double answer = 0;
		for (int i = 0; i < 2; i++) {
			double candidate = new Polygon(points[i], points[i + 1], new Segment(points[i + 1], points[i + 2]).middle(),
				new Segment(points[(i + 3) & 3], points[i]).middle()).square();
			answer = Math.max(answer, Math.min(candidate, total - candidate));
		}
		for (int i = 0; i < 2; i++) {
			double candidate = new Polygon(points[i], points[i + 1], points[i + 2]).square();
			answer = Math.max(answer, Math.min(candidate, total - candidate));
		}
		for (int i = 0; i < 4; i++) {
			double candidate = new Polygon(points[i], points[(i + 1) & 3],
				new Segment(points[(i + 1) & 3], points[(i + 2) & 3]).middle()).square();
			answer = Math.max(answer, Math.min(candidate, total - candidate));
		}
		for (int i = 0; i < 4; i++) {
			double candidate = new Polygon(points[i], points[(i + 3) & 3],
				new Segment(points[(i + 3) & 3], points[(i + 2) & 3]).middle()).square();
			answer = Math.max(answer, Math.min(candidate, total - candidate));
		}
		for (int i = 0; i < 4; i++) {
			double candidate = new Polygon(points[i], new Segment(points[i], points[(i + 1) & 3]).middle(),
				new Segment(points[(i + 3) & 3], points[i]).middle()).square();
			answer = Math.max(answer, Math.min(candidate, total - candidate));
		}
		out.printLine(answer, total - answer);
	}
}
