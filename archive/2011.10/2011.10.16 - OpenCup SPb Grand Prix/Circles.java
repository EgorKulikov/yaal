import net.egork.utils.Exit;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Circles implements Solver {
	static class Point {
		final int x;
		final int y;
		int res = 0;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	Point[] p;
	Point[] p1;
	Point[] p2;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int r2 = in.readInt();
		if (n == 0 && r2 == 0) {
			Exit.exit(in, out);
			return;
		}
		p = new Point[n];
		for (int i = 0; i < n; ++i) {
			int x = in.readInt();
			int y = in.readInt();
			p[i] = new Point(x, y);
		}
		p1 = p.clone();
		Arrays.sort(p1, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if (o1.x != o2.x)
					return o1.x - o2.x;
				return o1.y - o2.y;
			}
		});
		p2 = p.clone();
		Arrays.sort(p2, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if (o1.x != o2.x)
					return o2.x - o1.x;
				return o1.y - o2.y;
			}
		});
		for (int mdx = 0; mdx * mdx <= r2; ++mdx) {
			int mdy = (int) Math.sqrt(r2 - mdx * mdx);
			if (mdx * mdx + mdy * mdy == r2) {
				doit(mdx, mdy, p1, false);
				if (mdx != 0 && mdy != 0)
					doit(-mdx, mdy, p2, true);
			}
		}
		for (int i = 0; i < n; ++i) {
			if (i > 0) out.print(" ");
			out.print(p[i].res);
		}
		out.println();			
	}

	private void doit(int dx, int dy, Point[] p, boolean revX) {
		int j = 0;
		for (int i = 0; i < p.length; ++i) {
			int needX = p[i].x + dx;
			int needY = p[i].y + dy;
			if (i == j)
				++j;
			while (j < p.length) {
				if (revX) {
					if (p[j].x < needX)
						break;
					if (p[j].x > needX) {
						++j;
						continue;
					}
				} else {
					if (p[j].x > needX)
						break;
					if (p[j].x < needX) {
						++j;
						continue;
					}
				}
				if (p[j].y > needY)
					break;
				if (p[j].y < needY) {
					++j;
					continue;
				}
				++p[i].res;
				++p[j].res;
				break;
			}
		}
	}
}

