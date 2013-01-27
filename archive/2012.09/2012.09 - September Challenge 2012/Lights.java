package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Lights {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double from = in.readDouble();
		double to = in.readDouble();
		double[] x = new double[count];
		double[] y = new double[count];
		double[] angle = new double[count];
		IOUtils.readDoubleArrays(in, x, y, angle);
		for (int i = 0; i < count; i++)
			angle[i] = Math.tan(angle[i] / 180d * Math.PI);
		int[] order = ArrayUtils.order(x);
		double left = 0;
		double right = 1000;
		for (int i = 0; i < 100; i++) {
			double middle = (left + right) / 2;
			double current = from;
			for (int j : order) {
				if (y[j] <= middle)
					continue;
				double length = angle[j] * (y[j] - middle);
				if (current > x[j] - length)
					current = Math.max(current, x[j] + length);
			}
			if (current > to)
				left = middle;
			else
				right = middle;
		}
		out.printLine((left + right) / 2);
	}
}
