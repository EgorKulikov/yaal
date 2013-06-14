package net.egork;

import net.egork.numbers.Rational;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;

public class Gun {

	public static void main(String[] args) throws FileNotFoundException {
		Random random = new Random(1424643747L);
		while (true) {
			int n = random.nextInt(6) + 1;
			int m = random.nextInt(6) + 1;
			boolean[][] a = new boolean[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					a[i][j] = random.nextBoolean();
				}
			}
			new Gun().solve(new OutputWriter(new FileOutputStream("output.txt")),
				n, m, a);
		}
	}
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		boolean[][] a = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			int x = in.readInt();
			for (int j = 0; j < x; j++) {
				a[i][in.readInt() - 1] = true;
			}
		}

		solve(out, n, m, a);
	}

	private void solve(OutputWriter out, int n, int m, boolean[][] a) {
		boolean[] remove = new boolean[m];
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				boolean same = true;
				for (int k = 0; k < n; k++) {
					if(a[i][k] != a[j][k]) {
						same = false;
					}
				}
				if (same) {
					remove[j] = true;
				}
			}
		}

		int mm = 0;
		for (int i = 0; i < m; i++) {
			if (!remove[i]) {
				a[mm] = a[i];
				mm++;
			}
		}
		m = mm;

		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				int s = 0;
				for (int k = 0; k < n; k++) {
					if (a[i][k] && a[j][k]) s++;
				}
				if (s >= 2) {
					out.printLine("NO");
					return;
				}
			}
		}

		int[] d = new int[n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j]) d[j]++;
			}
		}

		int[] p = new int[n];
		for (int i = 0; i < n; i++) p[i] = i;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (d[p[j]] > d[p[i]]) {
					int t = p[i];
					p[i] = p[j];
					p[j] = t;
				}
			}
		}

		Rational[][] ll = new Rational[m][];

		int ccc = 0;
		if (d[p[0]] < 3) {
			ll[0] = buildLine(0, 0, 1, 0);
			for (int i = 1; i < m; i++) {
				ll[i] = buildLine(0, - i * i, i, 0);
			}
		} else if (n == 1 || d[p[1]] < 3) {
			ccc = 1;
			int k = 0;
			for (int i = 0; i < m; i++) {
				if (a[i][p[0]]) {
					ll[i] = buildLine(0, 0, 1, k);
					k++;
				}
			}
			int x = 1;
			for (int i = 0; i < m; i++) {
				if (!a[i][p[0]]) {
					ll[i] = buildLine(0, -k * x, x, 0);
					x++;
					k++;
				}
			}
		} else if (n == 2 || d[p[2]] < 3) {
			ccc = 2;
			for (int i = 0; i < m; i++) {
				if (a[i][p[0]] && a[i][p[1]]) {
					ll[i] = buildLine(0, 0, 1, 0);
				}
			}
			int k = 1;
			for (int i = 0; i < m; i++) {
				if (a[i][p[0]] && !a[i][p[1]]) {
					ll[i] = buildLine(0, 0, 1, k);
					k++;
				}
			}
			k = 1;
			for (int i = 0; i < m; i++) {
				if (!a[i][p[0]] && a[i][p[1]]) {
					ll[i] = buildLine(0, k, 1, 0);
					k++;
				}
			}

			for (int i = 0; i < m; i++) {
				if (!a[i][p[0]] && !a[i][p[1]]) {
					ll[i] = buildLine(0, -10, 1, -1);
					k++;
				}
			}
		} else {
			ccc = 3;
			for (int i = 0; i < m; i++) {
				if (a[i][p[0]] && a[i][p[1]] && !a[i][p[2]]) {
					ll[i] = buildLine(0, 0, 1, 0);
				}
				if (a[i][p[0]] && !a[i][p[1]] && a[i][p[2]]) {
					ll[i] = buildLine(0, 0, 1, 1);
				}
				if (!a[i][p[0]] && a[i][p[1]] && a[i][p[2]]) {
					ll[i] = buildLine(1, 1, 2, 0);
				}
				if (a[i][p[0]] && !a[i][p[1]] && !a[i][p[2]]) {
					ll[i] = buildLine(-3, -1, 0, 0);
				}
				if (!a[i][p[0]] && a[i][p[1]] && !a[i][p[2]]) {
					ll[i] = buildLine(1, -2, 2, 0);
				}
				if (!a[i][p[0]] && !a[i][p[1]] && a[i][p[2]]) {
					ll[i] = buildLine(-3, -1, 1, 1);
				}
			}
		}

		Rational[] x = new Rational[n];
		Rational[] y = new Rational[n];

		int xx = 20;

		for (int i = 0; i < n; i++) {
			if (d[i] >= 2) {
				int j = 0;
				while (!a[j][i]) j++;
				int k = j + 1;
				while (!a[k][i]) k++;

				x[i] = (ll[k][1].subtract(ll[j][1])).divide(ll[j][0].subtract(ll[k][0]));
				y[i] = ll[j][0].multiply(x[i]).add(ll[j][1]);
			} else {
				int j = 0;
				while (j < m && !a[j][i]) j++;
				if (j == m) {
					x[i] = new Rational(xx, 1);
					y[i] = Rational.ONE;
					xx++;
				} else {
					x[i] = new Rational(xx, 1);
					y[i] = ll[j][0].multiply(x[i]).add(ll[j][1]);
					xx++;
				}
			}
		}


		long lcmx = 1;
		for (int i = 0; i < n; i++) {
			lcmx = lcm(lcmx, x[i].denominator);
		}

		long lcmy = 1;
		for (int i = 0; i < n; i++) {
			lcmy = lcm(lcmy, y[i].denominator);
		}

		out.printLine("YES");
		for (int i = 0; i < n; i++) {
			out.printLine((x[i].multiply(lcmx).numerator + 5000) + " " + (y[i].multiply(lcmy).numerator + 5000));
		}
	}

	private long lcm(long a, long b) {
		long res = a * b;
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		res /= a;
		return res;
	}

	private Rational[] buildLine(int x1, int y1, int x2, int y2) {
		Rational[] res = new Rational[2];
		// (y1 - y2) / (x1 - x2);
		res[0] = new Rational(y1 - y2, x1 - x2);
		res[1] = new Rational(y1 * res[0].denominator - res[0].numerator * x1, res[0].denominator);
		return res;
	}
}
