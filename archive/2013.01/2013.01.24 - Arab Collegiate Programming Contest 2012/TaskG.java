package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double answer = 0;
		for (int i = 0; i < count; i++) {
			int x1 = in.readInt();
			int y1 = in.readInt();
			int x2 = in.readInt();
			int y2 = in.readInt();
			double angle1 = Math.atan2(y1, x1);
			double angle2 = Math.atan2(y2, x2);
			double angle = angle1 - angle2;
			while (angle < -Math.PI)
				angle += 2 * Math.PI;
			while (angle > Math.PI)
				angle -= 2 * Math.PI;
			answer += Math.abs(angle) / (2 * Math.PI);
		}
		out.printLine(answer);
    }
}
