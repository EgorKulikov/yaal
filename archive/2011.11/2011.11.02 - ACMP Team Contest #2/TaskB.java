package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		double x0 = in.readDouble();
		double y0 = in.readDouble();
		double z0 = in.readDouble();
		double r0 = in.readDouble();
		int count = in.readInt();
		double[] x = new double[count + 1];
		double[] y = new double[count + 1];
		double[] z = new double[count + 1];
		double[] r = new double[count + 1];
		x[0] = x0;
		y[0] = y0;
		z[0] = z0;
		r[0] = r0;
		for (int i = 1; i <= count; i++) {
			x[i] = in.readDouble();
			y[i] = in.readDouble();
			z[i] = in.readDouble();
			r[i] = in.readDouble();
		}
		boolean[] intersected = new boolean[count + 1];
		int nonIntersected = 1;
		for (int i = 1; i <= count; i++) {
			nonIntersected++;
			for (int j = 0; j < i; j++) {
				double totalRadius = r[i] + r[j];
				if (Math.abs(x[i] - x[j]) > totalRadius || Math.abs(y[i] - y[j]) > totalRadius || Math.abs(z[i] - z[j]) > totalRadius)
					continue;
				if (GeometryUtils.fastHypot(x[i] - x[j], y[i] - y[j], z[i] - z[j]) <= totalRadius - GeometryUtils.epsilon) {
					if (!intersected[j])
						nonIntersected--;
					intersected[j] = true;
					if (!intersected[i])
						nonIntersected--;
					intersected[i] = true;
				}
			}
			if (nonIntersected == 0) {
				out.printLine(i);
				return;
			}
		}
		out.printLine(0);
	}
}
