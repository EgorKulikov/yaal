package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		int count = in.readInt();
		int length = in.readInt();
		in.readInt();
		double answer = Double.POSITIVE_INFINITY;
		Segment[] segments = new Segment[count];
		for (int i = 0; i < count; i++) {
			int yi = in.readInt();
			int xf = in.readInt();
			int yf = in.readInt();
			int xi = i % 2 == 0 ? 0 : length;
			answer = Math.min(answer, length - Math.abs(xi - xf));
			segments[i] = new Segment(new Point(xi, yi), new Point(xf, yf));
		}
		for (int i = 1; i < count; i++)
			answer = Math.min(answer, segments[i].distance(segments[i - 1].b));
		out.printFormat("%.2f\n", answer);
	}
}
