package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildSimpleGraph(count, from, to);
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++)
			points[i] = Point.readPoint(in);
		Map<Point, Integer> answer = new HashMap<Point, Integer>();
		int[] size = new int[count];
		countSize(0, -1, graph, size);
		angle = new double[count];
		Integer[] order = ArrayUtils.generateOrder(count);
		for (int i = 1; i < count; i++) {
			if (points[order[i]].x < points[order[0]].x) {
				int temp = order[i];
				order[i] = order[0];
				order[0] = temp;
			}
		}
		go(0, -1, 0, count - 1, points, order, answer, graph, size, -Math.PI / 2);
		out.print(answer.get(points[0]));
		for (int i = 1; i < count; i++)
			out.print(" " + answer.get(points[i]));
		out.printLine();
	}

	double[] angle;

	private int countSize(int vertex, int last, int[][] graph, int[] size) {
		size[vertex] = 1;
		for (int i : graph[vertex]) {
			if (i != last)
				size[vertex] += countSize(i, vertex, graph, size);
		}
		return size[vertex];
	}

	private void go(int vertex, int last, final int from, int to, final Point[] points, Integer[] order, Map<Point, Integer> answer, int[][] graph, int[] size, double baseAngle) {
		answer.put(points[order[from]], vertex + 1);
		if (size[vertex] == 1)
			return;
		for (int i = from + 1; i <= to; i++) {
			angle[order[i]] = pretty(Math.atan2(points[order[i]].y - points[order[from]].y, points[order[i]].x - points[order[from]].x) - baseAngle);
			if (angle[order[i]] > Math.PI + GeometryUtils.epsilon)
				throw new RuntimeException();
		}
		Arrays.sort(order, from + 1, to + 1, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Double.compare(angle[o1], angle[o2]);
			}
		});
		int start = from + 1;
		for (int i : graph[vertex]) {
			if (i != last) {
				go(i, vertex, start, start + size[i] - 1, points, order, answer, graph, size, Math.atan2(points[order[start]].y - points[order[from]].y, points[order[start]].x - points[order[from]].x));
				start += size[i];
			}
		}
	}

	private double pretty(double v) {
		v %= 2 * Math.PI;
		if (v < 0)
			v += 2 * Math.PI;
		return v;
	}

}
