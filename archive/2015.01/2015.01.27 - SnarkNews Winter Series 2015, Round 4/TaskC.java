package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int diameter = in.readInt();
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++) {
			points[i] = Point.readPoint(in);
		}
		if (count == 1) {
			out.printLine(0);
			return;
		}
		Point[] convex = Polygon.convexHull(points).vertices;
		count = convex.length;
		Point[] rotated = new Point[convex.length];
		double answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				double length = convex[i].distance(convex[j]);
				double cosa = (convex[j].x - convex[i].x) / length;
				double sina = (convex[j].y - convex[i].y) / length;
				for (int k = 0; k < count; k++) {
					double dx = convex[k].x - convex[i].x;
					double dy = convex[k].y - convex[i].y;
					rotated[k] = new Point(dx * cosa + dy * sina, -dx * sina + dy * cosa);
				}
				double minA = -Math.PI / 2;
				double maxA = Math.PI / 2;
				for (int k = 0; k < count; k++) {
					if (k == i || k == j) {
						continue;
					}
					double ang1 = Math.atan2(rotated[k].y - rotated[i].y, rotated[k].x - rotated[i].x);
					double ang2 = Math.atan2(rotated[k].y - rotated[j].y, rotated[k].x - rotated[j].x);
					if (rotated[k].y > 0) {
						maxA = Math.min(maxA, Math.PI / 2 - ang1);
						minA = Math.max(minA, Math.PI / 2 - ang2);
					} else {
						maxA = Math.min(maxA, -Math.PI / 2 - ang2);
						minA = Math.max(minA, -Math.PI / 2 - ang1);
					}
				}
				if (length <= diameter) {
					answer += calculate(diameter, length, minA, maxA);
				} else {
					double required = Math.acos(diameter / length);
					answer += calculate(diameter, length, minA, -required);
					answer += calculate(diameter, length, required, maxA);
				}
			}
		}
		answer /= Math.PI;
		out.printLine(1 - answer);
	}

	private double calculate(int diameter, double length, double from, double to) {
		if (from >= to) {
			return 0;
		}
		return (diameter * (to - from) + length * (Math.sin(from) - Math.sin(to))) / diameter;
	}
}
