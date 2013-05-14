package net.egork;

import net.egork.geometry.*;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TheMuseum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int width = in.readInt();
		int length = in.readInt();
		int count = in.readInt();
		int x = in.readInt();
		int y = in.readInt();
		Point origin = new Point(x, y);
		List<SpecialSegment> segments = new ArrayList<SpecialSegment>();
		segments.add(new SpecialSegment(new Point(0, 0), new Point(width, 0), null));
		segments.add(new SpecialSegment(new Point(0, 0), new Point(0, length), null));
		segments.add(new SpecialSegment(new Point(width, length), new Point(width, 0), null));
		segments.add(new SpecialSegment(new Point(width, length), new Point(0, length), null));
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(0, 0));
		points.add(new Point(width, 0));
		points.add(new Point(0, length));
		points.add(new Point(width, length));
		for (int i = 0; i < count; i++) {
			char type = in.readCharacter();
			if (type == 'C') {
				int cx = in.readInt();
				int cy = in.readInt();
				int radius = in.readInt();
				Circle circle = new Circle(new Point(cx, cy), radius);
				if (circle.contains(origin))
					throw new RuntimeException();
				Point[] touching = circle.findTouchingPoints(origin);
				segments.add(new SpecialSegment(touching[0], touching[1], circle));
				points.add(touching[0]);
				points.add(touching[1]);
			} else {
				int vertexCount = in.readInt();
				Point[] vertices = new Point[vertexCount];
				for (int j = 0; j < vertexCount; j++)
					vertices[j] = Point.readPoint(in);
				points.addAll(Arrays.asList(vertices));
				for (int j = 0; j < vertexCount; j++)
					segments.add(new SpecialSegment(vertices[j], vertices[(j + 1) % vertexCount], null));
				if (new Polygon(vertices).contains(origin))
					throw new RuntimeException();
			}
		}
		List<Double> angles = new ArrayList<Double>();
		for (Point p : points)
			angles.add(Math.atan2(p.y - y, p.x - x));
		Collections.sort(angles);
		angles.add(angles.get(0) + 2 * Math.PI);
		double answer = 0;
		for (int i = 1; i < angles.size(); i++) {
			double left = angles.get(i - 1);
			double right = angles.get(i);
			if (Math.abs(right - left) < GeometryUtils.epsilon)
				continue;
			double angle = (left + right) / 2;
			if (angle > Math.PI)
				angle -= 2 * Math.PI;
			Line line = new Line(origin, angle);
			SpecialSegment best = null;
			double distance = Double.POSITIVE_INFINITY;
			for (SpecialSegment s : segments) {
				Point p = line.intersect(s.line());
				if (p == null || !s.contains(p, true))
					continue;
				double testAngle = Math.atan2(p.y - y, p.x - x);
				if (Math.abs(testAngle - angle) > Math.PI / 2) {
					if (Math.abs(Math.abs(testAngle - angle) - Math.PI) > GeometryUtils.epsilon)
						throw new RuntimeException();
					continue;
				}
				if (Math.abs(testAngle - angle) > GeometryUtils.epsilon)
					throw new RuntimeException();
				double curDistance = origin.distance(p);
				if (curDistance < distance) {
					distance = curDistance;
					best = s;
				}
			}
			if (best.circle == null) {
				Point p1 = best.line().intersect(new Line(origin, left));
				Point p2 = best.line().intersect(new Line(origin, right));
				answer += Polygon.triangleSquare(origin, p1, p2);
			} else {
				Point[] pp1 = new Line(origin, left).intersect(best.circle);
				Point[] pp2 = new Line(origin, right).intersect(best.circle);
				Point p1;
				if (pp1.length == 1 || pp1[0].distance(origin) < pp1[1].distance(origin))
					p1 = pp1[0];
				else
					p1 = pp1[1];
				Point p2;
				if (pp2.length == 1 || pp2[0].distance(origin) < pp2[1].distance(origin))
					p2 = pp2[0];
				else
					p2 = pp2[1];
				answer += Polygon.triangleSquare(origin, p1, p2);
				double d = p1.distance(p2) / 2;
				double r = best.circle.radius;
				double alpha = Math.asin(d / r);
				answer -= alpha * r * r;
				answer += d * Math.sqrt(r * r - d * d);
			}
		}
		out.printFormat("%.2f\n", answer / width / length * 100);
    }

	static class SpecialSegment extends Segment {
		private final Circle circle;

		public SpecialSegment(Point a, Point b, Circle circle) {
			super(a, b);
			this.circle = circle;
		}
	}
}
