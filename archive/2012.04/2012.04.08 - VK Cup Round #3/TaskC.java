package net.egork;

import net.egork.collections.sequence.Array;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.WeightedFlowEdge;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int workerCount = in.readInt();
		int[] start = new int[count];
		int[] time = new int[count];
		int[] profit = new int[count];
		IOUtils.readIntArrays(in, start, time, profit);
		int[] interestingTimes = new int[2 * count];
		for (int i = 0; i < count; i++) {
			interestingTimes[2 * i] = start[i];
			interestingTimes[2 * i + 1] = start[i] + time[i];
		}
		Arrays.sort(interestingTimes);
		Graph graph = new Graph(2 * count + 1);
		for (int i = 1; i < 2 * count; i++)
			graph.add(new FlowEdge(i - 1, i, workerCount));
		FlowEdge[] edges = new FlowEdge[count];
		for (int i = 0; i < count; i++) {
			graph.add(edges[i] = new WeightedFlowEdge(Arrays.binarySearch(interestingTimes, start[i]),
				Arrays.binarySearch(interestingTimes, start[i] + time[i]), -profit[i], 1));
		}
		graph.add(new FlowEdge(2 * count, 0, workerCount));
		GraphAlgorithms.minCostMaxFlow(graph, 2 * count, 2 * count - 1);
		int[] answer = new int[count];
		for (int i = 0; i < count; i++)
			answer[i] = (int) edges[i].getFlow();
		out.printLine(Array.wrap(answer).toArray());
	}
}
