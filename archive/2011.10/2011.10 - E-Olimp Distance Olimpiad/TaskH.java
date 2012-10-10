package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int index = in.readInt();
		double x = 0;
		double delta = .5;
		double y = Math.sqrt(3.) / 2;
		for (int i = 1; i < index; i++) {
			x += 2 * delta + 1;
			delta += 1;
			y += Math.sqrt(3.);
		}
		out.printf("%.3f %.3f\n", x, y);
	}
}
