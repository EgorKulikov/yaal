package net.egork;

import net.egork.collections.Pair;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] points = new Point[count];
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		for (int i = 0; i < count; i++)
			points[i] = new Point(x[i], y[i]);
		Segment[] segments = new Segment[count];
		for (int i = 0; i < count; i++)
			segments[i] = new Segment(points[i], points[(i + 1) % count]);
		double answer = 0;
		Polygon polygon = new Polygon(points);
		Map<Pair<Integer, Integer>, Integer>[] next = new Map[count];
		for (int i = 0; i < count; i++) {
			next[i] = new HashMap<Pair<Integer, Integer>, Integer>();
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				int dx = x[i] - x[j];
				int dy = y[i] - y[j];
				int g = IntegerUtils.gcd(Math.abs(dx), Math.abs(dy));
				dx /= g;
				dy /= g;
				if (Math.abs(i - j) == 1 || i == count - 1 && j == 0 || i == 0 && j == count - 1) {
					next[i].put(Pair.makePair(dx, dy), j);
					continue;
				}
				int a = y[i] - y[j];
				int b = x[j] - x[i];
				int c = -a * x[i] - b * y[i];
				Segment segment = new Segment(points[i], points[j]);
				boolean good = true;
				for (int k = 0; k < count; k++) {
					int value = a * x[k] + b * y[k] + c;
					if (value == 0) {
						if (k != i && k != j && segment.contains(points[k], false)) {
							good = false;
							break;
						}
					}
					int nxt = k + 1;
					if (nxt == count)
						nxt = 0;
					int nextValue = a * x[nxt] + b * y[nxt] + c;
					if (nextValue == 0)
						continue;
					if (segment.intersect(segments[k], false) != null) {
						good = false;
						break;
					}
				}
				if (!good)
					continue;
				Point middle = segment.middle();
				if (polygon.contains(middle)) {
					next[i].put(Pair.makePair(dx, dy), j);
				}
			}
		}
		for (int i = 0; i < count; i++) {
			for (Pair<Integer, Integer> pair : next[i].keySet()) {
				if (next[i].containsKey(Pair.makePair(-pair.first, -pair.second)))
					continue;
				int current = i;
				while (next[current].containsKey(pair))
					current = next[current].get(pair);
				double currentResult = points[i].distance(points[current]);
				int a = y[current] - y[i];
				int b = x[i] - x[current];
				int c = -x[i] * a - y[i] * b;
				Line line = new Line(a, b, c);
				int curNext = (i + 1) % count;
				int curLast = (i + count - 1) % count;
				int nextValue = a * x[curNext] + b * y[curNext] + c;
				int lastValue = a * x[curLast] + b * y[curLast] + c;
				if (nextValue < 0 || lastValue > 0) {
					double max = Double.POSITIVE_INFINITY;
					for (int k = 0; k < count; k++) {
						long value = a * x[k] + b * y[k] + c;
						if (value == 0)
							continue;
						int nxt = k + 1;
						if (nxt == count)
							nxt = 0;
						long nV = a * x[nxt] + b * y[nxt] + c;
						if (nV == 0)
							continue;
						if (value * nV > 0)
							continue;
						Point point = segments[k].line().intersect(line);
						double d1 = point.distance(points[i]);
						double d2 = point.distance(points[current]);
						if (d1 < d2)
							max = Math.min(max, d1);
					}
					currentResult += max;
				}
				curNext = (current + 1) % count;
				curLast = (current + count - 1) % count;
				nextValue = a * x[curNext] + b * y[curNext] + c;
				lastValue = a * x[curLast] + b * y[curLast] + c;
				if (nextValue > 0 || lastValue < 0) {
					double max = Double.POSITIVE_INFINITY;
					for (int k = 0; k < count; k++) {
						long value = a * x[k] + b * y[k] + c;
						if (value == 0)
							continue;
						int nxt = k + 1;
						if (nxt == count)
							nxt = 0;
						long nV = a * x[nxt] + b * y[nxt] + c;
						if (nV == 0)
							continue;
						if (value * nV > 0)
							continue;
						Point point = segments[k].line().intersect(line);
						double d1 = point.distance(points[i]);
						double d2 = point.distance(points[current]);
						if (d1 > d2)
							max = Math.min(max, d2);
					}
					currentResult += max;
				}
				answer = Math.max(answer, currentResult);
			}
		}
		out.printLine(answer);
    }
}
