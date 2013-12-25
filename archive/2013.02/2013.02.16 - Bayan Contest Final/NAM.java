package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class NAM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int startTime = in.readInt();
		int presidentCount = in.readInt();
		int[] arriveCity = new int[presidentCount];
		int[] arriveTime = new int[presidentCount];
		IOUtils.readIntArrays(in, arriveCity, arriveTime);
		MiscUtils.decreaseByOne(arriveCity);
		int maxAttendance = solve(count, from, to, arriveCity, arriveTime, startTime);
		int left = 0;
		int right = startTime;
		while (left < right) {
			int middle = (left + right) >> 1;
			if (solve(count, from, to, arriveCity, arriveTime, middle) == maxAttendance)
				right = middle;
			else
				left = middle + 1;
		}
		out.printLine(maxAttendance, left);
    }

	private int solve(int count, int[] from, int[] to, int[] arriveCity, int[] arriveTime, int maxTime) {
		Graph<Pair<Integer, Integer>> graph = new Graph<Pair<Integer, Integer>>();
		for (int i = 0; i < maxTime; i++) {
			for (int j = 0; j < from.length; j++)
				graph.addFlowEdge(Pair.makePair(i, from[j]), Pair.makePair(i + 1, to[j]), 1);
			for (int j = 0; j < count; j++)
				graph.addFlowEdge(Pair.makePair(i, j), Pair.makePair(i + 1, j), arriveCity.length);
		}
		Pair<Integer, Integer> source = Pair.makePair(-1, -1);
		graph.addVertex(source);
		Pair<Integer, Integer> destination = Pair.makePair(maxTime, count - 1);
		graph.addVertex(destination);
		for (int i = 0; i < arriveCity.length; i++) {
			if (arriveTime[i] <= maxTime)
				graph.addFlowEdge(source, Pair.makePair(arriveTime[i], arriveCity[i]), 1);
		}
		return (int) MaxFlow.dinic(graph, source, destination);
	}
}
