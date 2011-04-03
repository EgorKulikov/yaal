import net.egork.io.IOUtils;
import net.egork.numbers.NumberAlgorithms;
import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class GraphsInEuclideanSpace implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		int[][] distances = new int[n][];
		for (int i = 0; i < n; i++)
			distances[i] = in.readIntArray(n);
		int[][] result = null;
		double value = Double.POSITIVE_INFINITY;
		int[] occupied = new int[10500];
		Arrays.fill(occupied, -1);
		for (int i = 0; i < Math.min(n, 150); i++) {
			int[][] candidate = create(i, distances, occupied);
			double candidateValue = evaluate(candidate, distances);
			if (candidateValue < value) {
				value = candidateValue;
				result = candidate;
			}
		}
		assert result != null;
		out.println(result[0].length);
		for (int i = 0; i < n; i++)
			IOUtils.printArray(result[i], out);
	}

	private double evaluate(int[][] candidate, int[][] distances) {
		double minimum = Double.POSITIVE_INFINITY;
		double maximum = 0;
		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++) {
				double actualDistance = calculateDistance(candidate[i], candidate[j]);
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
			distance = NumberAlgorithms.fastHypot(distance, first[i] - second[i]);
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
}
