package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskG {
	private double eps = 1e-12;
	private double sqeps = 1e-8;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int cx = in.readInt();
		int cy = in.readInt();
		int cz = in.readInt();
		double r = in.readInt();
		double[] x = new double[3];
		double[] y = new double[3];
		double[] z = new double[3];
		for (int i = 0; i < 3; i++) {
			x[i] = in.readInt();
			y[i] = in.readInt();
			z[i] = in.readInt();
		}
		for (int i = 0; i < 2; i++) {
			x[i] -= cx;
			y[i] -= cy;
			z[i] -= cz;
		}
		rotate(z, x);
		rotate(z, y);
//		if (Math.abs(x[2]) > eps || Math.abs(y[2]) > eps || z[2] < eps)
//			while (true);
		if (Math.hypot(x[0], y[0]) > r + eps && Math.hypot(x[1], y[1]) > r + eps) {
			double a = y[0] - y[1];
			double b = x[1] - x[0];
			if (Math.abs(a) < eps && Math.abs(b) < eps) {
				out.printLine("False alarm");
				return;
			}
			double c = -a * x[0] - b * y[0];
			double hypot = Math.hypot(a, b);
			double value = Math.abs(c / hypot);
//			if (value > r + eps) {
//				out.printLine("False alarm");
//				return;
//			}
			double a1 = -b;
			double b1 = a;
			double c1 = 0;
			double xx = (c1 * b - c * b1) / (a * b1 - a1 * b);
			double yy = (c1 * a - c * a1) / (a1 * b - a * b1);
			double zz;
			if (Math.abs(b) > eps)
				zz = (z[1] * x[0] - z[1] * xx - x[1] * z[0] + xx * z[0]) / (x[0] - x[1]);
			else
				zz = (z[1] * y[0] - z[1] * yy - y[1] * z[0] + yy * z[0]) / (y[0] - y[1]);
			if (Math.hypot(xx, yy) > r + eps) {
				out.printLine("False alarm");
				return;
			}
			if (outside(x, y, xx, yy)) {
				out.printLine("False alarm");
				return;
			}
			if (zz <= 0)
				out.printLine("Warning");
			else
				out.printLine("False alarm");
			return;
		}
		double[] candidates = new double[4];
		for (int i = 0; i < 2; i++) {
			if (Math.hypot(x[i], y[i]) > r + eps) {
				candidates[2 * i] = candidates[2 * i + 1] = -1;
				continue;
			}
			double zz = Math.sqrt(r * r - x[i] * x[i] - y[i] * y[i]);
			if (Double.isNaN(zz))
				zz = 0;
			candidates[2 * i] = (zz - z[i]);
			candidates[2 * i + 1] = (-zz - z[i]);
		}
		Arrays.sort(candidates);
		if (candidates[3] < 0) {
			out.printLine("False alarm");
			return;
		}
		double time = -1;
		for (int i = 0; i < 4; i++) {
			if (candidates[i] > 0) {
				time = candidates[i];
				break;
			}
		}
		for (int i = 0; i < 2; i++)
			z[i] += time;
		double aa = Math.hypot(Math.hypot(x[0], y[0]), z[0]);
		double bb = Math.hypot(Math.hypot(x[1], y[1]), z[1]);
		double cc = Math.hypot(Math.hypot(x[0] - x[1], y[0] - y[1]), z[0] - z[1]);
		if (aa * aa + cc * cc > bb * bb + sqeps && bb * bb + cc * cc > aa * aa + sqeps) {
			out.printLine("Warning");
		} else {
			out.printLine("Crash");
		}
		if (true)
			return;
		int index = -1;
		double value = Double.POSITIVE_INFINITY;
		for (int i = 0; i < 2; i++) {
			if (Math.hypot(x[i], Math.hypot(y[i], z[i])) < value) {
				index = i;
				value = Math.hypot(x[i], Math.hypot(y[i], z[i]));
			}
		}
		if (Math.abs(value - r) > 1e-3)
			while (true);
		double a = x[index];
		double b = y[index];
		double c = z[index];
		double d = -r * r;
		if (a * x[1 - index] + b * y[1 - index] + c * z[1 - index] + d < -eps * r)
			out.printLine("Warning");
		else
			out.printLine("Crash");
	}

	private boolean outside(double[] x, double[] y, double xx, double yy) {
		return xx + eps < Math.min(x[0], x[1]) || xx > Math.max(x[0], x[1]) + eps || yy + eps < Math.min(y[0], y[1]) || yy > Math.max(y[0], y[1]) + eps;
	}

	private void rotate(double[] x, double[] y) {
		double hypot = Math.hypot(x[2], y[2]);
		if (hypot < eps)
			return;
		double cosa = x[2] / hypot;
		double sina = y[2] / hypot;
		for (int i = 0; i < 3; i++) {
			double hh = Math.hypot(x[i], y[i]);
			double nx = x[i] * cosa + y[i] * sina;
			double ny = -x[i] * sina + y[i] * cosa;
			double hhh = Math.hypot(nx, ny);
			if (hhh > eps) {
				nx *= hh / hhh;
				ny *= hh / hhh;
			}
			x[i] = nx;
			y[i] = ny;
		}
	}
}
