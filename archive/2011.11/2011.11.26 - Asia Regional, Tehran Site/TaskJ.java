package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.Pair;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	private double[][] perimeter;
	private double[][] dist;
	private double[][] delta;
	private boolean[][][][] intersect;
	private int[] order;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		dist = new double[count][count];
		delta = new double[count][count];
		order = new int[count + 1];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				dist[i][j] = GeometryUtils.fastHypot(x[i] - x[j], y[i] - y[j]);
				delta[i][j] = (x[i] - x[j]) * (y[i] + y[j]);
			}
		}
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = new Point(x[i], y[i]);
		Segment[][] segments = new Segment[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i != j)
					segments[i][j] = new Segment(points[i], points[j]);
			}
		}
		intersect = new boolean[count][count][count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < count; k++) {
					if (k == i || k == j)
						continue;
					for (int l = 0; l < count; l++) {
						if (l == i || l == j || l == k)
							continue;
						intersect[i][j][k][l] = segments[i][j].intersect(segments[k][l], true) != null;
					}
				}
			}
		}
		perimeter = new double[count][1 << count];
		ArrayUtils.fill(perimeter, -1);
		double minPerimeter = goPerimeter(0, 1);
		double minSquarePerimeter = goSquare(0, 0, 0, 0).second;
		out.printFormat("%.4f\n", minSquarePerimeter - minPerimeter);
	}

	private Pair<Double, Double> goSquare(int current, int mask, double square, double perimeter) {
		if (mask == this.perimeter[0].length - 1)
			return Pair.makePair(Math.abs(square), perimeter);
		int step = Integer.bitCount(mask) + 1;
		Pair<Double, Double> result = Pair.makePair(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		for (int i = (mask == this.perimeter[0].length - 2 ? 0 : 1); i < delta.length; i++) {
			if ((mask >> i & 1) == 1)
				continue;
			order[step] = i;
			boolean good = true;
			for (int j = (i == 0 ? 2 : 1); j < step - 1 && good; j++) {
				if (intersect[order[step - 1]][i][order[j - 1]][order[j]])
					good = false;
			}
			if (!good)
				continue;
			Pair<Double, Double> call = goSquare(i, mask + (1 << i), square + delta[current][i], perimeter + dist[current][i]);
			if (call.first + GeometryUtils.epsilon < result.first || call.first - GeometryUtils.epsilon < result.first && call.second < result.second)
				result = call;
		}
		return result;
	}

	private double goPerimeter(int current, int mask) {
		if (perimeter[current][mask] != -1)
			return perimeter[current][mask];
		if (mask == perimeter[0].length - 1)
			return perimeter[current][mask] = dist[current][0];
		perimeter[current][mask] = Double.POSITIVE_INFINITY;
		for (int i = 0; i < perimeter.length; i++) {
			if ((mask >> i & 1) == 0)
				perimeter[current][mask] = Math.min(perimeter[current][mask], dist[current][i] + goPerimeter(i, mask + (1 << i)));
		}
		return perimeter[current][mask];
	}
}
