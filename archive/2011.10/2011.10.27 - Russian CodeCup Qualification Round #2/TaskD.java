package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int alpha = in.readInt();
		int speed = in.readInt();
		int g = in.readInt();
		int x = in.readInt();
		int y = in.readInt();
		double vx = speed * Math.cos(alpha * Math.PI / 180);
		double vy = speed * Math.sin(alpha * Math.PI / 180);
		double a = g / 2.;
		double b = -g * x / vx;
		double c = x * vy / vx - y;
		if (c < 0) {
			out.println("Impossible");
			return;
		}
		double d = b * b - 4 * a * c;
		if (d < 0) {
			out.println("Impossible");
			return;
		}
		double t = (-b - Math.sqrt(d)) / (2 * a);
		out.printf("%.8f\n", t);
	}
}
