package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		double x0 = in.readDouble();
		double y0 = in.readDouble();
		double x = in.readDouble();
		double y = in.readDouble();
		double r = in.readDouble();
		double distance = GeometryUtils.fastHypot(x0 - x, y0 - y);
		if (distance <= r) {
			out.printLine(Math.PI * r * r);
			return;
		}
		double side = Math.sqrt((distance - r) * (distance + r));
		double angle = Math.acos(r / distance);
		double answer = side * r + (Math.PI - angle) * r * r;
		out.printLine(answer);
	}
}
