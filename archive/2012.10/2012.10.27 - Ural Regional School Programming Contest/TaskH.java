package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskH {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x11 = in.readInt();
		int y11 = in.readInt();
		int x12 = in.readInt();
		int y12 = in.readInt();
		int x21 = in.readInt();
		int y21 = in.readInt();
		int x22 = in.readInt();
		int y22 = in.readInt();
		int dx1 = x12 - x11;
		int dy1 = y12 - y11;
		int dx2 = x22 - x21;
		int dy2 = y22 - y21;
		if (dx1 * dy2 == dx2 * dy1) {
			int a = x11 - x12;
			int b = y11 - y12;
			int v11 = x11 * a + y11 * b;
			int v12 = x12 * a + y12 * b;
			int v21 = x21 * a + y21 * b;
			int v22 = x22 * a + y22 * b;
			if (Math.min(v11, v12) >= Math.max(v21, v22) || Math.min(v21, v22) >= Math.max(v11, v12))
				out.printLine(0);
			else
				out.printLine(-1);
			return;
		}
		double alpha = Math.atan2(dy1, dx1) - Math.atan2(dy2, dx2);
		double answer = Math.hypot(dx1, dy1) * Math.hypot(dx2, dy2) / Math.abs(Math.sin(alpha));
		out.printLine(answer);
	}
}
