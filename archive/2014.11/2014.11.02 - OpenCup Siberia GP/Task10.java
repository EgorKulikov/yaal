package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task10 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double xo = in.readDouble();
		double yo = in.readDouble();
		double xs = in.readDouble();
		double ys = in.readDouble();
		double xb = in.readDouble();
		double yb = in.readDouble();
		double x1 = xo - xb;
		double y1 = yo - yb;
		double x2 = xs - xb;
		double y2 = ys - yb;
		double a = Math.hypot(x1, y1);
		double d = Math.hypot(x2, y2);
		double al = Math.abs(Math.atan2(
			x1 * y2 - x2 * y1,
			x1 * x2 + y1 * y2
		));
		if (Math.abs(a - d) < 1e-9) {
			out.printLine(a * al);
		} else if (al < 1e-9) {
			out.printLine(Math.abs(d - a));
		} else {
			double b = Math.log(d / a) / al;
			double r = (d - a) / b;
			double rr = r * Math.sqrt(1 + b * b);
			out.printLine(rr);
		}
    }
}
