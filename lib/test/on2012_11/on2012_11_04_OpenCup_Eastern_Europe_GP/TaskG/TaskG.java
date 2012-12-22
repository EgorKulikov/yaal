package on2012_11.on2012_11_04_OpenCup_Eastern_Europe_GP.TaskG;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskG {
	static class PointData implements Comparable<PointData> {
		Point what;
		int index;
		double by;

		public int compareTo(PointData o) {
			return Double.compare(by, o.by);
		}
	}

	static class Timer {
		long last = System.currentTimeMillis();

		void time(String tag) {
			long cur = System.currentTimeMillis();
			System.err.println(tag + " " + (cur - last));
			last = cur;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Timer t = new Timer();
		int n = in.readInt();
		int m = in.readInt();
		Point[] mustHave = new Point[n];
		Point[] additional = new Point[m];
		for (int i = 0; i < n; ++i) mustHave[i] = Point.readPoint(in);
		for (int i = 0; i < m; ++i) additional[i] = Point.readPoint(in);
		Polygon p = Polygon.convexHull(mustHave);
		t.time("!!!");
		Point origin = p.center();
		n = p.vertices.length;
		Line[] sides = new Line[n];
		int[] insideSign = new int[n];
		long[] sideAreas = new long[n];
		for (int i = 0; i < n; ++i) {
			Point a = p.vertices[i];
			Point b = p.vertices[(i + 1) % n];
			sides[i] = a.line(b);
			insideSign[i] = sides[i].value(origin) >= 0 ? 1 : -1;
			sideAreas[i] = Math.round(-a.x + b.x) * Math.round(a.y + b.y);
		}
		long[] prefixSums = new long[2 * n + 1];
		prefixSums[0] = 0;
		for (int i = 0; i < 2 * n; ++i) {
			prefixSums[i + 1] = prefixSums[i] + sideAreas[i % n];
		}
		long startingArea = prefixSums[n];

		PointData[] pd = new PointData[additional.length];

		for (int i = 0; i < additional.length; ++i) {
			pd[i] = new PointData();
			pd[i].what = additional[i];
			pd[i].index = i;
			pd[i].by = Math.atan2(additional[i].y - origin.y, additional[i].x - origin.x);
		}
		t.time("???");
		Arrays.sort(pd);
		t.time("===");

		int lastNegative = -1;
		int lastPositive = -1;
		long[] areas = new long[additional.length];

		int badTimes = 0;

		for (PointData extra : pd) {
			Line l = origin.line(extra.what);
			if (l == null) {
				areas[extra.index] = startingArea;
				continue;
			}
			if (lastNegative < 0 || l.value(p.vertices[(lastNegative + 1) % n]) < 0) {
				++badTimes;
				if (badTimes > 10) throw new RuntimeException();
				for (int i = 0; i < n; ++i)
					if (l.value(p.vertices[i]) < 0 && l.value(p.vertices[(i + 1) % n]) >= 0) {
						lastNegative = i;
						break;
					}
			} else {
				while (l.value(p.vertices[lastNegative]) >= 0)
					lastNegative = (lastNegative + n - 1) % n;
			}
			if (lastPositive < 0 || l.value(p.vertices[(lastPositive + 1) % n]) > 0) {
				++badTimes;
				if (badTimes > 10) throw new RuntimeException();
				for (int i = 0; i < n; ++i)
					if (l.value(p.vertices[i]) > 0 && l.value(p.vertices[(i + 1) % n]) <= 0) {
						lastPositive = i;
						break;
					}
			} else {
				while (l.value(p.vertices[lastPositive]) <= 0)
					lastPositive = (lastPositive + n - 1) % n;
			}
			double sNegative = sides[lastNegative].value(extra.what) * insideSign[lastNegative];
			double sPositive = sides[lastPositive].value(extra.what) * insideSign[lastPositive];
			if (sNegative >= 0 && sPositive >= 0) {
				areas[extra.index] = startingArea;
				continue;
			}
			int caliper1;
			{
				int left = lastNegative;
				int right = lastPositive;
				if (right <= left) right += n;
				while (right - left > 1) {
					int middle = (left + right) / 2;
					int mm = middle >= n ? middle - n : middle;
					if (sides[mm].value(extra.what) * insideSign[mm] < 0)
						left = middle;
					else
						right = middle;
				}
				caliper1 = right % n;
			}
			int caliper2;
			{
				int left = lastPositive;
				int right = lastNegative;
				if (right <= left) right += n;
				while (right - left > 1) {
					int middle = (left + right) / 2;
					int mm = middle >= n ? middle - n : middle;
					if (sides[mm].value(extra.what) * insideSign[mm] < 0)
						right = middle;
					else
						left = middle;
				}
				caliper2 = right % n;
			}
			int caliper2Big = caliper2;
			if (caliper2Big < caliper1)
				caliper2Big += n;
			areas[extra.index] = prefixSums[caliper2Big] - prefixSums[caliper1] + Math.round(-p.vertices[caliper2].x + extra.what.x) * Math.round(p.vertices[caliper2].y + extra.what.y) + Math.round(-extra.what.x + p.vertices[caliper1].x) * Math.round(extra.what.y + p.vertices[caliper1].y);
		}

		for (long x : areas) {
			doOut(x, out);
		}
		t.time("+++");
	}

	private void doOut(long x, OutputWriter out) {
		out.printLine(x / 2 + "." + (x % 2) * 5);
	}
}
