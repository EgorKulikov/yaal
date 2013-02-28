package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Random;

public class TaskBGen {
	Random random = new Random(239);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point[] points = new Point[8];
		Point[] five = new Point[5];
		while (true) {
			for (int i = 0; i < 8; i++)
				points[i] = new Point(random.nextInt((int) (2e8 + 1)) - 1e8, random.nextInt((int) (2e8 + 1)) - 1e8);
			boolean good = true;
			for (int j = 0; j < 256; j++) {
				if (Integer.bitCount(j) != 5)
					continue;
				int k = 0;
				for (int i = 0; i < 8; i++) {
					if ((j >> i & 1) == 1)
						five[k++] = points[i];
				}
				if (Polygon.convexHull(five).vertices.length == 5) {
					good = false;
					break;
				}
			}
			if (good) {
				for (int i = 0; i < 8; i++) {
					out.printLine(Math.round(points[i].x), Math.round(points[i].y));
				}
				return;
			}
		}
    }
}
