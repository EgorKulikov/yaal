package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Worm {
	static class Point {
		int id;
		int x;
		int y;
		int z;
	}

	static class Plane {
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Plane)) return false;

			Plane plane = (Plane) o;

			if (a != plane.a) return false;
			if (b != plane.b) return false;
			if (c != plane.c) return false;
			if (d != plane.d) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = (int) (a ^ (a >>> 32));
			result = 31 * result + (int) (b ^ (b >>> 32));
			result = 31 * result + (int) (c ^ (c >>> 32));
			result = 31 * result + (int) (d ^ (d >>> 32));
			return result;
		}

		long a;
		long b;
		long c;
		long d;
		double divBy;
		Point p1;
		Point p2;
		Point p3;

		public Plane(Point p1, Point p2, Point p3) {
			if (p1.id > p2.id) {
				Point tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			if (p2.id > p3.id) {
				Point tmp = p3;
				p3 = p2;
				p2 = tmp;
			}
			if (p1.id > p2.id) {
				Point tmp = p1;
				p1 = p2;
				p2 = tmp;
			}
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
			a = (p2.y - p1.y) * (p3.z - p1.z) - (p2.z - p1.z) * (p3.y - p1.y);
			b = (p2.z - p1.z) * (p3.x - p1.x) - (p2.x - p1.x) * (p3.z - p1.z);
			c = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
			d = -(a * p1.x + b * p1.y + c * p1.z);
		}

		void complete() {
			divBy = 1.0 / Math.sqrt(a * (double) a + b * (double) b + c * (double) c);
		}

		int side(Point p) {
			long z = a * p.x + b * p.y + c * p.z + d;
			if (z < 0) return -1;
			if (z > 0) return 1;
			return 0;
		}

		public boolean isBad() {
			return a == 0 && b == 0 && c == 0;
		}

		public double distance(Point p) {
			return Math.abs(divBy * (a * p.x + b * p.y + c * p.z + d));
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Point[] p = new Point[n];
		for (int i = 0; i < n; ++i) {
			p[i] = new Point();
			p[i].id = i;
			p[i].x = in.readInt();
			p[i].y = in.readInt();
			p[i].z = in.readInt();
		}
		Point min = p[0];
		for (Point pp : p) {
			if (pp.x < min.x || pp.x == min.x && pp.y < min.y || pp.x == min.x && pp.y == min.y && pp.z < min.z)
				min = pp;
		}
		List<Plane> sides = new ArrayList<Plane>();
		Set<Plane> seenSides = new HashSet<Plane>();
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j < n; ++j) {
				Plane pl = new Plane(min, p[i], p[j]);
				if (pl.isBad()) continue;
				boolean ok = isSide(p, pl);
				if (ok) {
					if (seenSides.add(pl)) sides.add(pl);
				}
			}
		int qt = 0;
		while (qt < sides.size()) {
			Plane start = sides.get(qt++);
			for (Point pp : p) {
				Plane pl = new Plane(start.p1, start.p2, pp);
				if (!pl.isBad() && isSide(p, pl)) {
					if (seenSides.add(pl)) sides.add(pl);
				}
				pl = new Plane(start.p3, start.p2, pp);
				if (!pl.isBad() && isSide(p, pl)) {
					if (seenSides.add(pl)) sides.add(pl);
				}
				pl = new Plane(start.p1, start.p3, pp);
				if (!pl.isBad() && isSide(p, pl)) {
					if (seenSides.add(pl)) sides.add(pl);
				}
			}
		}
		for (Plane s : sides) s.complete();
		int numQueries = in.readInt();
		for (int i = 0; i < numQueries; ++i) {
			Point pp = new Point();
			pp.x = in.readInt();
			pp.y = in.readInt();
			pp.z = in.readInt();
			double minn = 1e100;
			for (Plane s : sides) {
				double dist = s.distance(pp);
				minn = Math.min(minn, dist);
			}
			out.printLine(String.format("%.4f", minn));
		}
	}

	private boolean isSide(Point[] p, Plane pl) {
		boolean seenNegative = false;
		boolean seenPositive = false;
		for (Point pp : p) {
			int cur = pl.side(pp);
			if (cur > 0) {
				seenPositive = true;
				if (seenNegative) break;
			} else if (cur < 0) {
				seenNegative = true;
				if (seenPositive) break;
			}
		}
		return !seenPositive || !seenNegative;
	}
}
