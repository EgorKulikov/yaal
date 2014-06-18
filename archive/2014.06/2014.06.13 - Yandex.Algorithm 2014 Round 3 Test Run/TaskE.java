package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] x = new int[4];
		int[] y = new int[4];
		int[] z = new int[4];
		IOUtils.readIntArrays(in, x, y, z);
		int xx = 0;
		int yy = 0;
		for (int i = 0; i < 4; i++) {
			xx += x[i];
			x[i] *= 4;
			yy += y[i];
			y[i] *= 4;
		}
		int sumSign = 0;
		int zero = 0;
		for (int i = 0; i < 3; i++) {
			int a = y[(i + 1) % 3] - y[(i + 2) % 3];
			int b = x[(i + 2) % 3] - x[(i + 1) % 3];
			int c = -a * x[(i + 1) % 3] - b * y[(i + 1) % 3];
			int sign = Integer.signum(a * xx + b * yy + c);
			sumSign += sign;
			zero += sign == 0 ? 1 : 0;
		}
		if (Math.abs(sumSign) == 3)
			out.printLine("Standing");
		else if (Math.abs(sumSign) + zero == 3)
			out.printLine("Instable");
		else
			out.printLine("Falling");
    }
}
