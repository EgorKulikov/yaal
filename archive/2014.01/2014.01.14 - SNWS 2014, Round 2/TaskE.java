package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int level = in.readInt();
		int x0 = in.readInt();
		int y0 = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		int[] power = new int[count];
		IOUtils.readIntArrays(in, x, y, power);
		if (count == 0) {
			out.printLine(0);
			return;
		}
		double[] strength = new double[count];
		for (int i = 0; i < count; i++) {
			int dx = x0 - x[i];
			int dy = y0 - y[i];
			strength[i] = (double)power[i] / (dx * dx + dy * dy);
		}
		int at = 0;
		for (int i = 1; i < count; i++) {
			if (strength[i] > strength[at])
				at = i;
		}
		double sum = level;
		for (int i = 0; i < count; i++) {
			if (i != at)
				sum += strength[i];
		}
		if (strength[at] > 6 * sum)
			out.printLine(at + 1);
		else
			out.printLine(0);
    }
}
