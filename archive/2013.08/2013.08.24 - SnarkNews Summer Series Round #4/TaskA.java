package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int startFloor = in.readInt();
		int targetFloor = in.readInt();
		int startX = in.readInt();
		int startY = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		int[] from = new int[count];
		int[] to = new int[count];
		IOUtils.readIntArrays(in, x, y, from, to);
		int startIndex = -1;
		for (int i = 0; i < count; i++) {
			if (startX == x[i] && startY == y[i] && (startFloor >= from[i] && startFloor <= to[i] || startFloor >= to[i] && startFloor <= from[i])) {
				startIndex = i;
				break;
			}
		}
		if (startIndex == -1) {
			x = Arrays.copyOf(x, count + 1);
			x[count] = startX;
			y = Arrays.copyOf(y, count + 1);
			y[count] = startY;
			from = Arrays.copyOf(from, count + 1);
			from[count] = startFloor;
			to = Arrays.copyOf(to, count + 1);
			to[count] = startFloor;
			startIndex = count++;
		}
		double[] distance = new double[count];
		Arrays.fill(distance, 1e20);
		distance[startIndex] = 0;
		boolean[] processed = new boolean[count];
		for (int i = 0; i < count; i++) {
			int current = -1;
			double minDistance = 1e20;
			for (int j = 0; j < count; j++) {
				if (!processed[j] && distance[j] < minDistance) {
					minDistance = distance[j];
					current = j;
				}
			}
			if (current == -1)
				break;
			if (to[current] == targetFloor) {
				out.printLine(distance[current]);
				return;
			}
			int floor = to[current];
			processed[current] = true;
			for (int j = 0; j < count; j++) {
				if (!processed[j] && (from[j] <= floor && floor <= to[j] || from[j] >= floor && floor >= to[j])) {
					double curDist = distance[current] + distance(x[j] - x[current], y[j] - y[current]);
					distance[j] = Math.min(distance[j], curDist);
				}
			}
		}
		out.printLine(-1);
    }

	private double distance(double dx, double dy) {
		return Math.sqrt(dx * dx + dy * dy);
	}
}
