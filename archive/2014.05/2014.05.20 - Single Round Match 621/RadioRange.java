package net.egork;

import net.egork.geometry.Point;
import net.egork.misc.ArrayUtils;

public class RadioRange {
    public double RadiusProbability(int[] X, int[] Y, int[] R, int Z) {
		int count = X.length;
		double[] distance = new double[2 * count];
		boolean[] in = new boolean[2 * count];
		for (int i = 0; i < count; i++) {
			double current = Point.ORIGIN.distance(new Point(X[i], Y[i]));
			if (current > R[i])
				distance[2 * i] = current - R[i];
			distance[2 * i + 1] = current + R[i];
			in[2 * i] = true;
		}
		int[] order = ArrayUtils.order(distance);
		double last = 0;
		int delta = 0;
		double answer = 0;
		for (int i : order) {
			if (delta == 0) {
				double current = Math.min(distance[i], Z);
				answer += current - last;
			}
			if (distance[i] > Z) {
				last = Z;
				break;
			}
			last = distance[i];
			if (in[i])
				delta++;
			else
				delta--;
		}
		answer += Z - last;
		return answer / Z;
    }
}
