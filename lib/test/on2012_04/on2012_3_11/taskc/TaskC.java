package on2012_04.on2012_3_11.taskc;



import net.egork.collections.sequence.Array;
import net.egork.graph.*;
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
			graph.addFlowEdge(i - 1, i, workerCount);
		@SuppressWarnings("unchecked")
		Edge[] edges = new Edge[count];
		for (int i = 0; i < count; i++) {
			edges[i] = graph.edge(graph.addFlowWeightedEdge(Arrays.binarySearch(interestingTimes, start[i]),
				Arrays.binarySearch(interestingTimes, start[i] + time[i]), -profit[i], 1));
		}
		graph.addFlowEdge(2 * count, 0, workerCount);
		new MinCostFlow(graph, 2 * count, 2 * count - 1, true).minCostMaxFlow();
		int[] answer = new int[count];
		for (int i = 0; i < count; i++)
			answer[i] = (int) edges[i].getFlow();
		out.printLine(Array.wrap(answer).toArray());
	}
}
