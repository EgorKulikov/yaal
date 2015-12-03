package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		double[] x = new double[4];
		double[] y = new double[4];
		for (int i = 0; i < 4; i++) {
			x[i] = in.readDouble();
			y[i] = in.readDouble();
		}
		double s =
				5 * 5 * 4 +
						3 * calc(x, y, 1) +
						4 * calc(x, y, -1) +
						6 * calc(y, x, 1) +
						1 * calc(y, x, -1);
		s *= 5;
		s /= (5 * 5 * 5 - 1);
		out.printLine(s);
	}

	private double calc(double[] x, double[] y, int d) {
		x = x.clone();
		for (int i = 0; i < 4; i++) {
			x[i] *= d;
		}
		double res = 0;
		for (int i = 0; i < 4; i++) {
			double x1 = x[i]; double x2 = x[(i + 1) % 4];
			if (x1 == x2) continue;
			double y1 = y[i]; double y2 = y[(i + 1) % 4];
			double m = 1;
			if (x2 < x1) {
				double t = x1; x1 = x2; x2 = t;
				t = y1; y1 = y2; y2 = t;
				m = -1;
			}
			if (x1 > .5) {
				res += m * (x2 - x1) * (y1 + y2) / 2;
			} else if (x2 > .5) {
				double yy = y1 + (y2 - y1) * (.5 - x1) / (x2 - x1);
				res += m * (yy + y2) * (x2 - .5) / 2;
			}
		}
//		System.out.println(res);
		return Math.abs(res);
	}
}
