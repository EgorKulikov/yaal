package net.egork;

import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point center = Point.readPoint(in);
		in.readInt();
		double answer = 0;
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			Point flower = Point.readPoint(in);
			answer = Math.max(answer, flower.distance(center) + in.readInt());
		}
		out.printLine(answer);
    }
}
