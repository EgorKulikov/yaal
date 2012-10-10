package April2011.CodeforcesBetaRound67;

import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int xStart = in.readInt();
		int yStart = in.readInt();
		Point start = new Point(xStart, yStart);
		int xEnd = in.readInt();
		int yEnd = in.readInt();
		Point end = new Point(xEnd, yEnd);
		int pointCount = in.readInt();
		Point[] points = new Point[pointCount];
		for (int i = 0; i < pointCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		Segment path = new Segment(start, end);
		double[] edgePath = new double[2];
		int index = 0;
		List<Point> intersectionPoints = new ArrayList<Point>();
		for (int i = 0; i < pointCount; i++) {
			if (path.contains(points[i], false)) {
				Point next = points[(i + 1) % pointCount];
				Point last = points[(i + pointCount - 1) % pointCount];
				Line line = path.line();
				if (!line.contains(next) && !line.contains(last) && line.value(next) * line.value(last) < 0) {
					index = 1 - index;
					intersectionPoints.add(points[i]);
				}
			}
			Segment edge = new Segment(points[i], points[(i + 1) % pointCount]);
			Point intersection = path.intersect(edge, false);
			if (intersection == null)
				edgePath[index] += edge.length();
			else {
				edgePath[index] += new Segment(points[i], intersection).length();
				index = 1 - index;
				edgePath[index] += new Segment(intersection, points[(i + 1) % pointCount]).length();
				intersectionPoints.add(intersection);
			}
		}
		double result;
		if (intersectionPoints.size() == 0)
			result = path.length();
		else
			result = Math.min(path.length() + new Segment(intersectionPoints.get(0), intersectionPoints.get(1)).length(),
				path.length() - new Segment(intersectionPoints.get(0), intersectionPoints.get(1)).length() + Math.min(edgePath[0], edgePath[1]));
		out.printf("%.9f\n", result);
	}
}

