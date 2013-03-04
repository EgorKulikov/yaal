import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskJ implements Solver {
	private static final double EPS = 1e-11;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int curveCount = in.readInt();
		int pointCount = in.readInt();
		Point[] p = new Point[curveCount];
		Point[] q = new Point[curveCount];
		Point[] r = new Point[curveCount];
		for (int i = 0; i < curveCount; i++) {
			int px = in.readInt();
			int py = in.readInt();
			int qx = in.readInt();
			int qy = in.readInt();
			int rx = in.readInt();
			int ry = in.readInt();
			p[i] = new Point(px, py);
			q[i] = new Point(qx, qy);
			r[i] = new Point(rx, ry);
		}
		Point[] points = new Point[pointCount];
		for (int i = 0; i < pointCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		long result = 0;
		for (int i = 0; i < curveCount; i++) {
			List<Integer> good = new ArrayList<Integer>();
			double a = p[i].y - r[i].y;
			double b = r[i].x - p[i].x;
			double h = Math.hypot(a, b);
			a /= h;
			b /= h;
			double c = -p[i].x * a - p[i].y * b;
			double value = a * q[i].x + b * q[i].y + c;
			if (value > 0) {
				a = -a;
				b = -b;
				c = -c;
			}
			double dist = Math.hypot(p[i].x - r[i].x, p[i].y - r[i].y);
			double a1 = b;
			double b1 = -a;
			double c1 = -a1 * p[i].x - b1 * p[i].y;
			value = a1 * r[i].x + b1 * r[i].y + c1;
			double upTo = value;
			if (value < 0) {
				Point temp = p[i];
				p[i] = r[i];
				r[i] = temp;
				a1 = b;
				b1 = -a;
				c1 = -a1 * p[i].x - b1 * p[i].y;
				value = a1 * r[i].x + b1 * r[i].y + c1;
				upTo = value;
			}
			for (int j = 0; j < pointCount; j++) {
				value = points[j].x * a + points[j].y * b + c;
				if (value < EPS || value > 2 * dist - EPS)
					continue;
				value = points[j].x * a1 + points[j].y * b1 + c1;
				if (value < EPS || value > upTo - EPS)
					continue;
				good.add(j);
			}
			if (good.isEmpty())
				continue;
			Integer[] order1 = good.toArray(new Integer[good.size()]);
			Integer[] order2 = good.toArray(new Integer[good.size()]);
			final double[] angle1 = new double[pointCount];
			final double[] distance1 = new double[pointCount];
			double baseAngle = Math.atan2(r[i].y - p[i].y, r[i].x - p[i].x);
			for (int j : order1) {
				Point point = points[j];
				angle1[j] = Math.atan2(point.y - p[i].y, point.x - p[i].x) - baseAngle;
				while (angle1[j] < 0)
					angle1[j] += 2 * Math.PI;
				while (angle1[j] > 2 * Math.PI)
					angle1[j] -= 2 * Math.PI;
				distance1[j] = p[i].distance(point);
			}
			final double[] angle2 = new double[pointCount];
			final double[] distance2 = new double[pointCount];
			baseAngle = Math.atan2(-r[i].y + p[i].y, -r[i].x + p[i].x);
			for (int j : order2) {
				Point point = points[j];
				angle2[j] = baseAngle - Math.atan2(point.y - r[i].y, point.x - r[i].x);
				while (angle2[j] < 0)
					angle2[j] += 2 * Math.PI;
				while (angle2[j] > 2 * Math.PI)
					angle2[j] -= 2 * Math.PI;
				distance2[j] = r[i].distance(point);
			}
			Arrays.sort(order1, new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					if (Math.abs(angle1[o1] - angle1[o2]) > EPS)
						return Double.compare(angle1[o2], angle1[o1]);
					return Double.compare(distance1[o2], distance1[o1]);
				}
			});
			Arrays.sort(order2, new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					if (Math.abs(angle2[o1] - angle2[o2]) > EPS)
						return Double.compare(angle2[o1], angle2[o2]);
					return Double.compare(distance2[o1], distance2[o2]);
				}
			});
			int[] index = new int[pointCount];
			for (int j = 0; j < order2.length; j++)
				index[order2[j]] = j;
			IntervalTree tree = new IntervalTree(order1.length);

			for (int j : order1) {
				result += tree.get(index[j]);
				tree.add(index[j]);
			}
		}
		out.println(result);
	}

	private static class Point {
		private final double x;
		private final double y;

		private Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double distance(Point other) {
			double dx = x - other.x;
			double dy = y - other.y;
			return Math.sqrt(dx * dx + dy * dy);
		}
	}

	private static class IntervalTree {
		private int[] left;
		private int[] right;
		private int[] value;

		private IntervalTree(int size) {
			left = new int[4 * size];
			right = new int[4 * size];
			value = new int[4 * size];
			init(0, size, 0);
		}

		private void init(int left, int right, int root) {
			this.left[root] = left;
			this.right[root] = right;
			if (right - left > 1) {
				init(left, (left + right) / 2, 2 * root + 1);
				init((left + right) / 2, right, 2 * root + 2);
			}
		}

		public void add(int position) {
			add(position, 0);
		}

		private void add(int position, int root) {
			if (left[root] > position || right[root] <= position)
				return;
			value[root]++;
			if (right[root] - left[root] > 1) {
				add(position, 2 * root + 1);
				add(position, 2 * root + 2);
			}
		}

		public int get(int position) {
			return get(position, 0);
		}

		private int get(int position, int root) {
			if (left[root] >= position)
				return 0;
			if (right[root] <= position)
				return value[root];
			return get(position, 2 * root + 1) + get(position, 2 * root + 2);
		}
	}
}

