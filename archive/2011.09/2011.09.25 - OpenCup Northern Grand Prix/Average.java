import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.awt.Point;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class Average implements Solver {

	Point[] p;
	int n;
	int[] st;
	int sp;

	public void solve(int testNumber, InputReader in, PrintWriter out) {
		n = in.readInt();
		p = new Point[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Point(in.readInt(), in.readInt());
		}

		boolean ok = false;
		int z = 0;
		for (int i = 2; i < n; i++) {
			if (compare(p[i], p[1], p[0]) != 0) {
				z = i;
				ok = true;
				break;
			}
		}
		if (!ok || n == 3) {
			out.println(2 + "/" + 1);
			return;
		}
		int count1 = z - 1;
		int count2 = 1;
		for (int i = z + 1; i < n; i++) {
			int val1 = compare(p[i], p[1], p[0]);
			int val2 = compare(p[i], p[z], p[0]);
			if (val1 != 0 && val2 != 0) {
				ok = false;
				break;
			}
			if (val1 == 0)
				count1++;
			if (val2 == 0)
				count2++;
		}
		if (!(count1 > 1 && count2 > 1 || !ok)) {
			out.println((3 * n - 1) + "/" + n);
			return;
		}

		ok = false;
		for (int i = 3; i < n; i++) {
			if (compare(p[i], p[2], p[1]) != 0) {
				ok = true;
				break;
			}
		}

		if (!ok) {
			out.println((3 * n - 1) + "/" + n);
			return;			
		}

		ch();
		long res = 0;
		int m = sp - 1;
		for (int i = 0; i < n - m; i++) {
			res += m;
		}
		int[] st2 = new int[n + 1];
		int sp2 = 0;
		for (int i = 0; i < m - 1; i++) {
			sp2 = 1;
			st2[0] = st[i];
			for (int j = (i == 0) ? 0 : (st[i] + 1); j <= st[i + 2]; j++) if (j != st[i + 1]) {
//				System.err.println(i + " " + j);
				while (sp2 >= 2) {
					if (compare(p[j], p[st2[sp2 - 1]], p[st2[sp2 - 2]]) <= 0) {
						sp2--;
					} else {
						break;
					}
				}
				st2[sp2] = j;
				sp2++;
			}
			res += (m - 1 + sp2 - 2);
//			System.err.println(p[st[i + 1]] + ": " + sp2);
		}
		n--;
		ch();
		n++;
		res += (sp - 1);
		long den = n;
		long dd = BigInteger.valueOf(res).gcd(BigInteger.valueOf(den)).longValue();
		res /= dd;
		den /= dd;
		out.println(res + "/" + den);

	}

	private void ch() {
		int lb = 0;
		for (int i = 1; i < n; i++) {
			if (p[i].x < p[lb].x || p[i].x == p[lb].x && p[i].y < p[lb].y) {
				lb = i;
			}
		}
		Point t = p[n - 1];
		p[n - 1] = p[lb];
		p[lb] = t;

		final Point oo = p[n - 1];

		Arrays.sort(p, 0, n - 1, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				int x1 = o1.x - oo.x;
				int x2 = o2.x - oo.x;
				int y1 = o1.y - oo.y;
				int y2 = o2.y - oo.y;
				int res = Long.signum(1l * x1 * y2 - 1l * x2 * y1);
				if (res != 0)
					return res;
				return Long.signum(1l * x1 * x1 + 1l * y1 * y1 - (1l * x2 * x2 + 1l * y2 * y2));
			}
		});

		st = new int[n + 1];
		sp = 1;
		st[0] = n - 1;
		for (int i = 0; i < n; i++) {
			while (sp >= 2) {
				if (compare(p[i], p[st[sp - 1]], p[st[sp - 2]]) <= 0) {
					sp--;
				} else {
					break;
				}
			}
			st[sp] = i;
			sp++;
		}
	}

	int compare(Point a, Point b, Point o) {
		int x1 = a.x - o.x;
		int x2 = b.x - o.x;
		int y1 = a.y - o.y;
		int y2 = b.y - o.y;
		return Long.signum(1l * x1 * y2 - 1l * x2 * y1);
	}

}

