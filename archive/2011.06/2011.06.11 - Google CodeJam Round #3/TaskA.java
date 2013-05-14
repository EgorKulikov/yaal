import net.egork.geometry.*;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Locale;

public class TaskA implements Solver {
	static {
		Locale.setDefault(Locale.US);
		GeometryUtils.epsilon = 1e-11;
	}

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		double width = in.readDouble();
		int lowerPointCount = in.readInt();
		int upperPointCount = in.readInt();
		int guestCount = in.readInt();
		Point[] lowerPoint = new Point[lowerPointCount];
		for (int i = 0; i < lowerPointCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			lowerPoint[i] = new Point(x, y);
		}
		Point[] upperPoint = new Point[upperPointCount];
		for (int i = 0; i < upperPointCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			upperPoint[i] = new Point(x, y);
		}
		double totalArea = 0;
		for (int i = 1; i < lowerPointCount; i++)
			totalArea += (lowerPoint[i].x - lowerPoint[i - 1].x) * (lowerPoint[i].y + lowerPoint[i - 1].y);
		for (int i = 1; i < upperPointCount; i++)
			totalArea -= (upperPoint[i].x - upperPoint[i - 1].x) * (upperPoint[i].y + upperPoint[i - 1].y);
		totalArea = Math.abs(totalArea) / 2;
		int upperIndex = 0;
		int lowerIndex = 0;
		double partArea = totalArea / guestCount;
		double[] answer = new double[guestCount - 1];
		Segment upperSegment = new Segment(upperPoint[0], upperPoint[1]);
		Segment lowerSegment = new Segment(lowerPoint[0], lowerPoint[1]);
		double remainingArea = partArea;
		for (int i = 0; i < guestCount - 1; i++) {
			while (true) {
				double x = Math.min(upperSegment.b.x, lowerSegment.b.x);
				Line vertical = new Point(x, 0).line(new Point(x, 1));
				Point upperIntersection = upperSegment.line().intersect(vertical);
				Point lowerIntersection = lowerSegment.line().intersect(vertical);
				Polygon polygon = new Polygon(upperSegment.a, upperIntersection, lowerIntersection, lowerSegment.a);
				double square = polygon.square();
				if (square < remainingArea) {
					remainingArea -= square;
					upperSegment = new Segment(upperIntersection, upperPoint[upperIndex + 1]);
					lowerSegment = new Segment(lowerIntersection, lowerPoint[lowerIndex + 1]);
					if (upperSegment.b.x - upperSegment.a.x < GeometryUtils.epsilon) {
						upperSegment = new Segment(upperPoint[upperIndex + 1], upperPoint[upperIndex + 2]);
						upperIndex++;
					}
					if (lowerSegment.b.x - lowerSegment.a.x < GeometryUtils.epsilon) {
						lowerSegment = new Segment(lowerPoint[lowerIndex + 1], lowerPoint[lowerIndex + 2]);
						lowerIndex++;
					}
					continue;
				}
				double left = upperSegment.a.x;
				double right = x;
				while (right - left > GeometryUtils.epsilon) {
					x = (left + right) / 2;
					vertical = new Point(x, 0).line(new Point(x, 1));
					upperIntersection = upperSegment.line().intersect(vertical);
					lowerIntersection = lowerSegment.line().intersect(vertical);
					polygon = new Polygon(upperSegment.a, upperIntersection, lowerIntersection, lowerSegment.a);
					square = polygon.square();
					if (square < remainingArea)
						left = x;
					else
						right = x;
				}
				x = (left + right) / 2;
				vertical = new Point(x, 0).line(new Point(x, 1));
				upperIntersection = upperSegment.line().intersect(vertical);
				lowerIntersection = lowerSegment.line().intersect(vertical);
				upperSegment = new Segment(upperIntersection, upperPoint[upperIndex + 1]);
				if (upperSegment.length() < GeometryUtils.epsilon) {
					upperSegment = new Segment(upperPoint[upperIndex + 1], upperPoint[upperIndex + 2]);
					upperIndex++;
				}
				lowerSegment = new Segment(lowerIntersection, lowerPoint[lowerIndex + 1]);
				if (lowerSegment.length() < GeometryUtils.epsilon) {
					lowerSegment = new Segment(lowerPoint[lowerIndex + 1], lowerPoint[lowerIndex + 2]);
					lowerIndex++;
				}
				answer[i] = x;
				remainingArea = partArea;
				break;
			}
		}
		out.println("Case #" + testNumber + ":");
		for (double value : answer)
			out.printf("%.10f\n", value);
	}
}

