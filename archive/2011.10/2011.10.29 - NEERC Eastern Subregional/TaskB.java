package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int airportCount = in.readInt();
		int flightCount = in.readInt();
		int[] from = new int[flightCount];
		int[] to = new int[flightCount];
		final int[] departs = new int[flightCount];
		int[] length = new int[flightCount];
		int[] delayProbability = new int[flightCount];
		int[] delay = new int[flightCount];
		IOUtils.readIntArrays(in, from, to, departs, length, delayProbability, delay);
		MiscUtils.decreaseByOne(from, to);
		int[][] graph = GraphUtils.buildOrientedGraph(airportCount, from, to);
		for (int i = 0; i < airportCount; i++) {
			SequenceUtils.sort(Array.wrap(graph[i]), new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					return departs[o1] - departs[o2];
				}
			});
		}
		double[][] result = new double[airportCount][];
		for (int i = 0; i < airportCount; i++) {
			result[i] = new double[graph[i].length];
			Arrays.fill(result[i], -1);
		}
		double answer = new Dynamic(graph, to, departs, length, delayProbability, delay, result).go(0, 0);
		if (answer == Double.POSITIVE_INFINITY)
			out.println("Fail");
		else
			out.printf("%.9f\n", answer);
	}
}

class Dynamic {

	private final int[][] graph;
	private final int[] to;
	private final int[] departs;
	private final int[] length;
	private final int[] delayProbability;
	private final int[] delay;
	private final double[][] result;
	private final int count;

	public Dynamic(int[][] graph, int[] to, int[] departs, int[] length, int[] delayProbability, int[] delay,
		double[][] result)
	{
		count = graph.length;
		this.graph = graph;
		this.to = to;
		this.departs = departs;
		this.length = length;
		this.delayProbability = delayProbability;
		this.delay = delay;
		this.result = result;
	}

	public double go(int airport, int index) {
		if (airport == count - 1)
			return index;
		if (graph[airport].length == index)
			return Double.POSITIVE_INFINITY;
		if (result[airport][index] != -1)
			return result[airport][index];
		int flight = graph[airport][index];
		return result[airport][index] = Math.min(go(airport, index + 1),
			(go(to[flight], find(to[flight], departs[flight] + length[flight])) * (100 - delayProbability[flight]) +
			go(to[flight], find(to[flight], departs[flight] + length[flight] + delay[flight])) * delayProbability[flight]) / 100);
	}

	private int find(int airport, int time) {
		if (airport == count - 1)
			return time;
		int left = 0;
		int right = graph[airport].length;
		while (left < right) {
			int middle = (left + right) / 2;
			if (departs[graph[airport][middle]] < time)
				left = middle + 1;
			else
				right = middle;
		}
		return left;
	}
}