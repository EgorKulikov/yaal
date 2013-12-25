package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int width = in.readInt();
		int height = in.readInt();
		int count = in.readInt();
		int[] x0 = new int[count];
		int[] y0 = new int[count];
		int[] x1 = new int[count];
		int[] y1 = new int[count];
		IOUtils.readIntArrays(in, x0, y0, x1, y1);
		out.printLine(go(0, width, 0, height, x0, y0, x1, y1));
    }

	private int go(int fx, int tx, int fy, int ty, int[] x0, int[] y0, int[] x1, int[] y1) {
		for (int i = 0; i < x0.length; i++) {
			if (x0[i] >= fx && x1[i] <= tx && y0[i] >= fy && y1[i] <= ty) {
				if (x1[i] < tx) {
					boolean valid = true;
					for (int j = 0; j < x0.length && valid; j++) {
						if (x0[j] >= fx && x1[j] <= tx && y0[j] >= fy && y1[j] <= ty && x0[j] < x1[i] && x1[j] > x1[i])
							valid = false;
					}
					if (valid)
						return Math.max(go(fx, x1[i], fy, ty, x0, y0, x1, y1), go(x1[i], tx, fy, ty, x0, y0, x1, y1));
				}
				if (y1[i] < ty) {
					boolean valid = true;
					for (int j = 0; j < x0.length && valid; j++) {
						if (x0[j] >= fx && x1[j] <= tx && y0[j] >= fy && y1[j] <= ty && y0[j] < y1[i] && y1[j] > y1[i])
							valid = false;
					}
					if (valid)
						return Math.max(go(fx, tx, fy, y1[i], x0, y0, x1, y1), go(fx, tx, y1[i], ty, x0, y0, x1, y1));
				}
			}
		}
		return (tx - fx) * (ty - fy);
	}
}
