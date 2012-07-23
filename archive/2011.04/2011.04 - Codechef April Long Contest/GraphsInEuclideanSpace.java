package April2011.CodechefAprilLongContest;

import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class GraphsInEuclideanSpace implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[][] distances = new int[n][];
		for (int i = 0; i < n; i++)
			distances[i] = in.readIntArray(n);
		int[][] result = gravity(distances, 2, 42);
		double value = evaluate(result, distances);
		int[][] candidate = gravity(distances, 2, 100500);
		double candidateValue = evaluate(candidate, distances);
		if (candidateValue < value) {
			value = candidateValue;
			result = candidate;
		}
		candidate = gravity(distances, 2, 239);
		candidateValue = evaluate(candidate, distances);
		if (candidateValue < value) {
			value = candidateValue;
			result = candidate;
		}
		int[] occupied = new int[10500];
		Arrays.fill(occupied, -1);
		for (int i = 3; i <= 3; i++) {
			candidate = gravity(distances, i, 42);
			candidateValue = evaluate(candidate, distances);
			if (candidateValue < value) {
				value = candidateValue;
				result = candidate;
			}
			candidate = gravity(distances, i, 100500);
			candidateValue = evaluate(candidate, distances);
			if (candidateValue < value) {
				value = candidateValue;
				result = candidate;
			}
			candidate = gravity(distances, i, 239);
			candidateValue = evaluate(candidate, distances);
			if (candidateValue < value) {
				value = candidateValue;
				result = candidate;
			}
		}
		for (int i = 0; i < Math.min(n, 10); i++) {
			candidate = create(i, distances, occupied);
			candidateValue = evaluate(candidate, distances);
			if (candidateValue < value) {
				value = candidateValue;
				result = candidate;
			}
		}
/*		int index1 = -1;
		int index2 = -1;
		int maxDistance = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (distances[i][j] > maxDistance) {
					maxDistance = distances[i][j];
					index1 = i;
					index2 = j;
				}
			}
		}
		int[][] candidate = new int[n][1];
		candidate[index1][0] = 0;
		candidate[index2][0] = maxDistance;
		occupied[0] = n;
		occupied[maxDistance] = n;
		for (int i = 0; i < n; i++) {
			if (i == index1 || i == index2)
				continue;
			int position = (int) (((double)distances[index1][i] / (distances[index1][i] + distances[index2][i])) * maxDistance);
			while (occupied[position] == n)
				position++;
			candidate[i][0] = position;
			occupied[position] = n;
		}
		double candidateValue = evaluate(candidate, distances);
		if (candidateValue < value) {
			value = candidateValue;
			result = candidate;
		}
		System.err.println(evaluate(result, distances));*/
//		System.err.println(value);
		out.println(result[0].length);
		for (int i = 0; i < n; i++)
			IOUtils.printArray(result[i], out);
	}

	private double evaluate(int[][] candidate, int[][] distances) {
		double minimum = Double.POSITIVE_INFINITY;
		double maximum = 0;
		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++) {
				double actualDistance = GeometryUtils.fastHypot(candidate[i], candidate[j]);
//				for (int k = 0; k < candidate[i].length; k++) {
//					double diff = candidate[i][k] - candidate[j][k];
//					actualDistance += diff * diff;
//				}
				minimum = Math.min(minimum, actualDistance / distances[i][j]);
				maximum = Math.max(maximum, actualDistance / distances[i][j]);
			}
		}
		return maximum / minimum * candidate[0].length;
	}

	private double calculateDistance(int[] first, int[] second) {
		if (first.length == 1)
			return Math.abs(first[0] - second[0]);
		double distance = 0;
		for (int i = 0; i < first.length; i++)
			distance = GeometryUtils.fastHypot(distance, first[i] - second[i]);
		return distance;
	}

	private int[][] create(int position, int[][] distances, int[] occupied) {
		int[][] points = new int[distances.length][1];
		for (int i = 0; i < distances.length; i++) {
			int result = distances[position][i];
			while (occupied[result] == position)
				result++;
			occupied[result] = position;
			points[i][0] = result;
		}
		return points;
	}

	private int[][] gravity(int[][] distances, int p, int seed) {
		Random rand = new Random(seed);
		int n = distances.length;
		double[][] result = new double[n][p];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				result[i][j] = rand.nextInt(5000);
			}
		}
		double distanceRatio = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				double distance = GeometryUtils.fastHypot(result[i], result[j]);
				distanceRatio += distance / distances[i][j];
			}
		}
		distanceRatio /= n * (n - 1) / 2;
//		double[][] d = new double[n][p];
		double[] d = new double[p];
//		double[] weight = new double[n];
		for (int it = 0; it < 5; it++) {
//			for (int i = 0; i < n; i++)
//				Arrays.fill(d[i], 0);
//			Arrays.fill(weight, 0);
			for (int i = 0; i < n; i++) {
				Arrays.fill(d, 0);
				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;
					double distance = GeometryUtils.fastHypot(result[i], result[j]);
					double ratio = distances[i][j] * distanceRatio / distance - 1;
//					weight[i] += ratio * ratio;
//					weight[j] += ratio * ratio;
					for (int k = 0; k < p; k++) {
						d[k] -= ratio * (result[j][k] - result[i][k]);
//						d[j][k] += ratio * (result[j][k] - result[i][k]);
					}
				}
				for (int j = 0; j < p; j++)
					result[i][j] += d[j] / (n - 1);
			}
		}
		double maxAbs = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				maxAbs = Math.max(maxAbs, Math.abs(result[i][j]));
			}
		}
		int[][] realResult = new int[n][p];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				realResult[i][j] = (int) (result[i][j] / maxAbs * 500000);
				if (Math.abs(realResult[i][j]) > 1000000)
					throw new RuntimeException();
			}
		}
		return realResult;
	}
}

