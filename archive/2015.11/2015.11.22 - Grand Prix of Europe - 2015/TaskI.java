package net.egork;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.Arrays;

public class TaskI {

	public static final int SIZE = 500;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		double[][] r = new double[SIZE][SIZE];
		for (double[] doubles : r) {
			Arrays.fill(doubles, -1);
		}
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			r[x - 1][y - 1] = in.readDouble();
		}
		int q = in.readInt();
		for (int qq = 0; qq < q; qq++) {
			int x1 = in.readInt() - 1;
			int y1 = in.readInt() - 1;
			int x2 = in.readInt() - 1;
			int y2 = in.readInt() - 1;
			if (x2 < x1) {
				int t = x1; x1 = x2; x2 = t;
				t = y1; y1 = y2; y2 = t;
			}
			int res = 0;
			if (x1 == x2) {
				for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
					if (r[x1][i] > 0) {
						res++;
					}
				}
			} else {
				double a = y1 - y2;
				double b = x2 - x1;
				double d = Math.sqrt(a * a + b * b);
				a /= d;
				b /= d;
				double c = - x1 * a - y1 * b;
				for (int x = x1; x <= x2; x++) {
					int xx1 = Math.max(x - 1, x1);
					int xx2 = Math.min(x + 1, x2);
					int yy1 = (y1 * (x2 - x1) + (y2 - y1) * (xx1 - x1)) / (x2 - x1);
					int yy2 = (y1 * (x2 - x1) + (y2 - y1) * (xx2 - x1)) / (x2 - x1);

					for (int y = Math.min(yy1, yy2); y <= Math.max(yy1, yy2) + 1; y++) {
						if (y < Math.min(y1, y2)) continue;
						if (y > Math.max(y1, y2)) continue;
						if (r[x][y] > 0 && Math.abs(a * x + b * y + c) <= r[x][y]) {
							res++;
						}
					}
				}
			}
			out.printLine(res);
		}
	}
}
