package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	private int count;
	private int[] x;
	private int[] y;
	private int[] r;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int xa = in.readInt();
		int ya = in.readInt();
		int xb = in.readInt();
		int yb = in.readInt();
		count = in.readInt();
		x = new int[count];
		y = new int[count];
		r = new int[count];
		IOUtils.readIntArrays(in, x, y, r);
		int x0 = Math.min(xa, xb);
		int x1 = Math.max(xa, xb);
		int y0 = Math.min(ya, yb);
		int y1 = Math.max(ya, yb);
		int answer = 0;
		for (int i = x0; i <= x1; i++) {
			if (check(i, y0))
				answer++;
			if (check(i, y1))
				answer++;
		}
		for (int i = y0 + 1; i < y1; i++) {
			if (check(x0, i))
				answer++;
			if (check(x1, i))
				answer++;
		}
		out.printLine(answer);
	}

	private boolean check(int xx, int yy) {
		for (int i = 0; i < count; i++) {
			if ((x[i] - xx) * (x[i] - xx) + (y[i] - yy) * (y[i] - yy) <= r[i] * r[i])
				return false;
		}
		return true;
	}
}
