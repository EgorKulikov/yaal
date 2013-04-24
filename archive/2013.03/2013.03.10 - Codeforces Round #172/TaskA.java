package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long width = in.readInt();
		int height = in.readInt();
		int inDegrees = in.readInt();
		if (inDegrees == 0 || inDegrees == 180 || inDegrees == 90 && width == height) {
			out.printLine(width * height);
			return;
		}
		double alpha = inDegrees * Math.PI / 180;
		Point[] original = {new Point(width / 2d, height / 2d), new Point(width / 2d, -height / 2d),
			new Point(-width / 2d, -height / 2d), new Point(-width / 2d, height / 2d)};
		Point[] rotated = new Point[4];
		for (int i = 0; i < 4; i++) {
			rotated[i] = new Point(original[i].x * Math.cos(alpha) - original[i].y * Math.sin(alpha),
				original[i].x * Math.sin(alpha) + original[i].y * Math.cos(alpha));
		}
		double answer = 0;
		for (int i = 0; i < 4; i++) {
			Segment segment = new Segment(original[i], original[(i + 1) & 3]);
			List<Point> intersections = new ArrayList<Point>();
			for (int j = 0; j < 4; j++) {
				Point point = segment.intersect(new Segment(rotated[j], rotated[(j + 1) & 3]), false);
				if (point != null)
					intersections.add(point);
			}
			if (intersections.size() == 2)
				answer += segment.line().distance(new Point(0, 0)) * intersections.get(0).distance(intersections.get(1));
			else if (intersections.size() != 0)
				throw new RuntimeException();
		}
		out.printLine(answer);
    }
}
