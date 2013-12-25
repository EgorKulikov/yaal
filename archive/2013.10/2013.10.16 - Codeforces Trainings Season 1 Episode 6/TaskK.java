package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		int[] weight = new int[count];
		IOUtils.readIntArrays(in, x, y, weight);
		int x0 = Integer.MAX_VALUE;
		int y0 = Integer.MAX_VALUE;
		int x1 = 0;
		int y1 = 0;
		for (int i = 0; i < count; i++) {
			x0 = Math.min(x0, Math.min(x[i], y[i]));
			y0 = Math.min(y0, Math.max(x[i], y[i]));
			x1 = Math.max(x1, Math.max(x[i], y[i]));
			y1 = Math.max(y1, Math.min(x[i], y[i]));
		}
		long perimeter = 2l * (x1 + y1 - x0 - y0);
		int[] min = {x0, y0};
		int[] max = {x1, y1};
		int[] w = new int[4];
		boolean[][] rotate = new boolean[4][count];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (min[i] > max[j] || min[1 - i] > max[1 - j])
					continue;
				int k = 2 * i + j;
				boolean ok = true;
				for (int l = 0; l < count; l++) {
					if (x[l] < min[i] || x[l] > max[j] || y[l] < min[1 - i] || y[l] > max[1 - j]) {
						if (x[l] < min[1 - i] || x[l] > max[1 - j] || y[l] < min[i] || y[l] > max[j])
							ok = false;
						w[k] += weight[l];
						rotate[k][l] = true;
					}
				}
				if (!ok)
					w[k] = Integer.MAX_VALUE;
			}
		}
		int at = ArrayUtils.minPosition(w);
		out.printLine(perimeter, w[at]);
		for (boolean b : rotate[at]) {
			if (b)
				out.print('1');
			else
				out.print('0');
		}
		out.printLine();
    }
}
