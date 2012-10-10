package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int lost = in.readInt();
		if (count <= lost) {
			out.printLine(-1);
			return;
		}
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		Set<Point> answer = new HashSet<Point>();
		Set<Point> checked = new HashSet<Point>();
		Arrays.sort(points, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if (o1.y != o2.y)
					return o1.y - o2.y;
				return o1.x - o2.x;
			}
		});
		for (int i = 0; i <= lost; i++) {
			for (int j = 0; i + j <= lost; j++) {
				int x = points[i].x + points[count - j - 1].x;
				int y = points[i].y + points[count - j - 1].y;
				Point center = new Point(x, y);
				if (checked.contains(center))
					continue;
				checked.add(center);
				int remaining = lost - i - j;
				int index1 = i + 1;
				int index2 = count - j - 2;
				while (index1 <= index2 && remaining >= 0) {
					int value1 = y - points[index1].y - points[index2].y;
					int value2 = x - points[index1].x - points[index2].x;
					if (value1 == 0) {
						if (value2 == 0) {
							index1++;
							index2--;
						} else if (value2 > 0) {
							index1++;
							remaining--;
						} else {
							index2--;
							remaining--;
						}
					} else if (value1 > 0) {
						index1++;
						remaining--;
					} else {
						index2--;
						remaining--;
					}
				}
				if (remaining >= 0)
					answer.add(center);
			}
		}
		out.printLine(answer.size());
		for (Point point : answer)
			out.printFormat("%.1f %.1f\n", point.x / 2., point.y / 2.);
	}
}
