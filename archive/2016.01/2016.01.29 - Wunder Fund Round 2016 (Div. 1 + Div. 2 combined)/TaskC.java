package net.egork;

import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] x = new int[n];
		int[] y = new int[n];
		IOUtils.readIntArrays(in, x, y);
		Point[] starts = new Point[n];
		for (int i = 0; i < n; i++) {
			starts[i] = new Point(x[i], y[i]);
		}
		int[] answer = new int[3];
		answer[0] = 1;
		Point current = starts[0];
		int closest = 1;
		double distance = starts[1].distance(current);
		for (int i = 2; i < n; i++) {
			double candidate = current.distance(starts[i]);
			if (candidate < distance) {
				distance = candidate;
				closest = i;
			}
		}
		answer[1] = closest + 1;
		Line line = current.line(starts[closest]);
		long a = y[closest] - y[0];
		long b = x[0] - x[closest];
		long c = -a * x[0] - b * y[0];
		closest = -1;
		distance = Double.POSITIVE_INFINITY;
		for (int i = 0; i < n; i++) {
			if (a * x[i] + b * y[i] + c == 0) {
				continue;
			}
			double candidate = line.distance(starts[i]);
			if (candidate < distance) {
				distance = candidate;
				closest = i;
			}
		}
		answer[2] = closest + 1;
		out.printLine(answer);
	}
}
