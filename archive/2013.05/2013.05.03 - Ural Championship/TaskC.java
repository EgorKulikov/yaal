package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	static final int MAX_POW = 12;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] x = new int[n];
		int[] y = new int[n];
		IOUtils.readIntArrays(in, x, y);
		for (int i = n - 1; i >= 0; --i) {
			x[i] -= x[0];
			y[i] -= y[0];
		}
		double answer = 0;
		double total = 0;
		for (int i = 2; i < n; i++) {
			double s = square(x[i - 1], y[i - 1], x[i], y[i]);
			answer += s * s * s * 4 / 27;
			total += s * s;
			double mx1 = (x[i - 1] + x[i]) / 3d;
			double my1 = (y[i - 1] + y[i]) / 3d;
			for (int j = i + 1; j < n; j++) {
				double t = square(x[j - 1], y[j - 1], x[j], y[j]);
				double mx2 = (x[j - 1] + x[j]) / 3d;
				double my2 = (y[j - 1] + y[j]) / 3d;
				answer += 2 * s * t * square(mx1, my1, mx2, my2);
				total += 2 * s * t;
			}
		}
		out.printLine(answer / total);
    }

	double square(double x0, double y0, double x1, double y1) {
		return Math.abs(x0 * y0 + (x0 + x1) * (y1 - y0) - y1 * x1) / 2;
	}
}
