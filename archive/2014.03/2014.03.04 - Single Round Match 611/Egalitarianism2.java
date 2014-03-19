package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.geometry.GeometryUtils;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class Egalitarianism2 {
    public double minStdev(int[] x, int[] y) {
		int count = x.length;
		double[] dst = new double[count * (count - 1) / 2];
		final double[][] length = new double[count][count];
		int k = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++)
				length[i][j] = length[j][i] = dst[k++] = GeometryUtils.fastHypot(x[i] - x[j], y[i] - y[j]);
		}
//		double[] distances = new double[dst.length * (dst.length - 1) / 2 + 2];
		double[] distances = new double[dst.length * (dst.length - 1) / 2 + 2];
		distances[0] = 0;
		k = 1;
		for (int i = 0; i < dst.length; i++) {
			for (int j = 0; j < i; j++)
				distances[k++] = (dst[i] + dst[j]) / 2;
		}
		distances[k] = 3e6;
		Arrays.sort(distances);
		int[] order = ArrayUtils.createOrder(count * (count - 1) / 2);
		final int[] from = new int[order.length];
		final int[] to = new int[order.length];
		k = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < i; j++) {
				from[k] = i;
				to[k++] = j;
			}
		}
		double answer = Double.POSITIVE_INFINITY;
		double[] taken = new double[count - 1];
		for (int i = 1; i < distances.length; i++) {
			final double current = (distances[i - 1] + distances[i]) / 2;
			IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
			ArrayUtils.sort(order, new IntComparator() {
				public int compare(int first, int second) {
					return Double.compare(Math.abs(length[from[first]][to[first]] - current), Math.abs(length[from[second]][to[second]] - current));
				}
			});
			k = 0;
			for (int j : order) {
				if (setSystem.join(from[j], to[j]))
					taken[k++] = length[from[j]][to[j]];
			}
			double mean = 0;
			for (double d : taken)
				mean += d;
			mean /= count - 1;
			double stDev = 0;
			for (double d : taken)
				stDev += (d - mean) * (d - mean);
			stDev = Math.sqrt(stDev / (count - 1));
			answer = Math.min(answer, stDev);
		}
		return answer;
    }
}
