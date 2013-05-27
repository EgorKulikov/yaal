package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double[] a = readVecor(in);
		double[] va1 = readVecor(in);
		double[] va2 = readVecor(in);
		double[] b = readVecor(in);
		double[] vb1 = readVecor(in);
		double[] vb2 = readVecor(in);

		double[] na = vect(va1, va2);
		double[] nb = vect(vb1, vb2);
		double[] f = vect(na, nb);
		if (hypot(f) < 1e-6) {
			out.printLine("NO");
			return;
		}
		double[] fa = vect(f, na);
		double[] fb = vect(f, nb);

		double[] cf = solve(fa, f, fb, subt(b, a));

		double[] c = new double[3];
		for (int i = 0; i < 3; i++) {
			c[i] = a[i] + cf[0] * fa[i];
		}

		double[] s1 = solve(a, c, f);
		double[] s2 = solve(b, c, f);
		if (s1 == null || s2 == null ||
			s1[0] > s2[1] || s2[0] > s1[1] ||
			s1[0] > s2[0] && s1[1] < s2[1] ||
			s2[0] > s1[0] && s2[1] < s1[1]) {
			out.printLine("NO");
		} else {
			out.printLine("YES");
		}

    }

	private double[] solve(double[] a, double[] c, double[] f) {
		double aa = 0;
		double bb = 0;
		double cc = 0;
		for (int i = 0; i < 3; i++) {
			aa += f[i] * f[i];
			bb += 2 * (c[i] - a[i]) * f[i];
			cc += (c[i] - a[i]) * (c[i] - a[i]);
		}
		cc -= 1;
		double d = (bb * bb - 4 * aa * cc);
		if (d < 1e-12) return null;
		d = Math.sqrt(d);
		return new double[]{(-bb-d) / (2*aa), (-bb+d) / (2*aa)};
	}

	private double[] solve(double[] a, double[] b, double[] c, double[] d) {
		double[] res = new double[3];
		res[0] = det(d, b, c) / det(a, b, c);
		res[1] = det(a, d, c) / det(a, b, c);
		res[2] = det(a, b, d) / det(a, b, c);
		return res;
	}

	private double det(double[] a, double[] b, double[] c) {
		double res = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) if (i != j) {
				for (int k = 0; k < 3; k++) if (i != k && j != k) {
					if ((i < j) ^ (i < k) ^ (j < k)) {
						res -= a[i] * b[j] * c[k];
					} else {
						res += a[i] * b[j] * c[k];
					}
				}
			}
		}
		return res;
	}

	private double[] subt(double[] a, double[] b) {
		double[] res = new double[3];
		for (int i = 0; i < 3; i++) {
			res[i] = a[i] - b[i];
		}
		return res;
	}

	private double hypot(double[] f) {
		double res = 0;
		for (int i = 0; i < 3; i++) {
			res += f[i] * f[i];
		}
		return Math.sqrt(res);
	}

	private double[] vect(double[] a, double[] b) {
		double[] res = new double[3];
		for (int i = 0; i < 3; i++) {
			int j = (i + 1) % 3;
			int k = (i + 2) % 3;
		   	res[i] = a[j] * b[k] - a[k] * b[j];
		}
		return res;
	}

	private double[] readVecor(InputReader in) {
		double[] res = new double[3];
		for (int i = 0; i < 3; i++)
			res[i] = in.readDouble();
		return res;
	}


}
