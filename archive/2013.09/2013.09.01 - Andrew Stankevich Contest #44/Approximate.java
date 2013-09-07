package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;

public class Approximate {
	static class Point {
		double x;
		double y;
		boolean nice = false;
		int index;
	}

	int n;
	Point[] p;
	double res;
	double r;
	boolean solutionMode = true;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Locale.setDefault(Locale.US);
		n = in.readInt();
		r = in.readInt();
		if (n == 0 && r == 0) throw new UnknownError();
		p = new Point[n];
		res = 0;
		for (int i = 0; i < n; ++i) {
			p[i] = new Point();
			p[i].x = in.readInt();
			p[i].y = in.readInt();
			p[i].index = i;
		}
		solveOne();
		out.printLine(String.format("%.12f", res));
    }

	public static void main(String[] args) {
		Random random = new Random(342507341L);
		int cnt = 0;
		while (true) {
			++cnt;
			if (cnt % 1 == 0) System.out.println(cnt);
			Point[] p = new Point[5];
			for (int i = 0; i < p.length; ++i) {
				p[i] = new Point();
				p[i].index = i;
				p[i].x = random.nextInt(100);
				p[i].y = random.nextInt(100);
			}
			int n = p.length;
			int r = random.nextInt(100) + 1;
			Approximate a1 = new Approximate();
			a1.solutionMode = false;
			a1.n = n;
			a1.r = r;
			a1.p = p.clone();
			a1.solveOne();
			Approximate a2 = new Approximate();
			a2.solutionMode = true;
			a2.n = n;
			a2.r = r;
			a2.p = p.clone();
			a2.solveOne();
			if (Math.abs(a1.res - a2.res) > 1e-10 * Math.max(a1.res, a2.res)) throw new RuntimeException();
		}
	}

	private void solveOne() {
		if (true) {
			Arrays.sort(p, new Comparator<Point>() {
				public int compare(Point o1, Point o2) {
					return Double.compare(o1.x, o2.x);
				}
			});
			for (int i = 0; i < 4 && i < n; ++i) {
				p[i].nice = true;
				p[n - 1 - i].nice = true;
			}
			Arrays.sort(p, new Comparator<Point>() {
				public int compare(Point o1, Point o2) {
					return Double.compare(o1.y, o2.y);
				}
			});
			for (int i = 0; i < 4 && i < n; ++i) {
				p[i].nice = true;
				p[n - 1 - i].nice = true;
			}
			int cnt = 0;
			for (int i = 0; i < n; ++i) {
				if (p[i].nice) {
					p[cnt++] = p[i];
				}
			}
			Point[] tmp = new Point[cnt];
			System.arraycopy(p, 0, tmp, 0, cnt);
			p = tmp;
		}
		// top left bottom right
		handleCase(0, 1, 2, 3, 0);
		handleCase(0, 0, 1, 2, 1);
		handleCase(0, 1, 2, 0, 1);
		handleCase(0, 1, 1, 2, 1);
		handleCase(0, 1, 2, 2, 1);
		handleCase(0, 0, 1, 1, 2);
		handleCase(0, 1, 1, 0, 2);
	}

	private void handleCase(int itop, int ileft, int ibottom, int iright, int mode) {
		for (Point top : p)
			for (Point left : p) {
				if ((left == top) ^ (ileft == itop)) continue;
				for (Point bottom : p) {
					if ((bottom == top) ^ (ibottom == itop)) continue;
					if ((bottom == left) ^ (ibottom == ileft)) continue;
					if (top.y < bottom.y) continue;
					for (Point right : p) {
						if ((right == top) ^ (iright == itop)) continue;
						if ((right == left) ^ (iright == ileft)) continue;
						if ((right == bottom) ^ (iright == ibottom)) continue;
						if (left.x > right.x) continue;
						boolean topCover = false;
						boolean leftCover = false;
						boolean bottomCover = false;
						boolean rightCover = false;
						for (Point other : p) {
							if (other == top || other == left || other == bottom || other == right) continue;
							if (top.y < other.y || top.y == other.y && top.index < other.index) topCover = true;
							if (bottom.y > other.y || bottom.y == other.y && bottom.index < other.index) bottomCover = true;
							if (right.x < other.x || right.x == other.x && right.index < other.index) rightCover = true;
							if (left.x > other.x || left.x == other.x && left.index < other.index) leftCover = true;
						}
						int[] classCover = new int[4];
						++classCover[itop];
						++classCover[ibottom];
						++classCover[ileft];
						++classCover[iright];
						for (int i = 0; i < 4; ++i) if (classCover[i] > 0) ++classCover[i];
						if (topCover) --classCover[itop];
						if (bottomCover) --classCover[ibottom];
						if (leftCover) --classCover[ileft];
						if (rightCover) --classCover[iright];
						boolean totalCover = false;
						for (int i = 0; i < 4; ++i) if (classCover[i] == 1) totalCover = true;
						if (totalCover) continue;
						if (mode == 0) {
							res = Math.max(res, updateRes0(right.x - left.x + 2 * r, top.y - bottom.y + 2 * r));
						} else if (mode == 1) {
							res = Math.max(res, updateRes1(right.x - left.x + r, top.y - bottom.y + r));
						} else {
							res = Math.max(res, updateRes2(right.x - left.x, top.y - bottom.y));
						}
					}
				}
			}
	}

	private double updateRes2(double x, double y) {
		double res = Math.max(updateRes0(x + 2 * r, y), updateRes0(x, y + 2 * r));
		double left = 0;
		double right = Math.PI / 2;
		if (!solutionMode) {
			res = oneBlock2(x, y, res, left, right);
		} else {
			while (right - left > 1e-14) {
				double ml = (left * 2 + right) / 3;
				double mr = (left + 2 * right) / 3;
				double el = updateRes0(x + 2 * r * Math.cos(ml), y + 2 * r * Math.sin(ml));
				double er = updateRes0(x + 2 * r * Math.cos(mr), y + 2 * r * Math.sin(mr));
				res = Math.max(res, el);
				res = Math.max(res, er);
				if (el > er) {
					right = mr;
				} else {
					left = ml;
				}
			}
		}
		return res;
	}

	private double oneBlock2(double x, double y, double res, double left, double right) {
		while (right - left > 1e-14) {
			double ml = (left * 2 + right) / 3;
			double mr = (left + 2 * right) / 3;
			double el = updateRes1(x + r * Math.cos(ml), y + r * Math.sin(ml));
			double er = updateRes1(x + r * Math.cos(mr), y + r * Math.sin(mr));
			res = Math.max(res, el);
			res = Math.max(res, er);
			if (el > er) {
				right = mr;
			} else {
				left = ml;
			}
		}
		return res;
	}

	private double updateRes1(double x, double y) {
		double res = Math.max(updateRes0(x + r, y), updateRes0(x, y + r));
		double left = 0;
		double right = Math.PI / 2;
		while (right - left > 1e-14) {
			double ml = (left * 2 + right) / 3;
			double mr = (left + 2 * right) / 3;
			double el = updateRes0(x + r * Math.cos(ml), y + r * Math.sin(ml));
			double er = updateRes0(x + r * Math.cos(mr), y + r * Math.sin(mr));
			res = Math.max(res, el);
			res = Math.max(res, er);
			if (el > er) {
				right = mr;
			} else {
				left = ml;
			}
		}
		return res;
	}

	private double updateRes0(double x, double y) {
		return x * y;
	}
}
