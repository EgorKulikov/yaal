package Timus.Part4;

import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class Task1348 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int ax = in.readInt();
		int ay = in.readInt();
		Point a = new Point(ax, ay);
		int bx = in.readInt();
		int by = in.readInt();
		Point b = new Point(bx, by);
		int cx = in.readInt();
		int cy = in.readInt();
		Point c = new Point(cx, cy);
		int l = in.readInt();
		out.printf("%.2f\n", Math.max(0, new Segment(a, b).distance(c) - l));
		out.printf("%.2f\n", Math.max(0, Math.max(a.distance(c), b.distance(c)) - l));
	}
}

