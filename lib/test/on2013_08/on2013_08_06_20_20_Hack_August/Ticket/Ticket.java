package on2013_08.on2013_08_06_20_20_Hack_August.Ticket;



import net.egork.collections.map.EHashMap;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class Ticket {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int windowCount = in.readInt();
		int destinationCount = in.readInt();
		Map<String, Integer> price = new EHashMap<String, Integer>();
		for (int i = 0; i < destinationCount; i++) {
			String name = in.readString();
			price.put(name, in.readInt() * 5);
		}
		Graph graph = new Graph(2 * count + 1);
		String[] destinations = IOUtils.readStringArray(in, count);
		for (int i = 0; i < count; i++) {
			int curPrice = price.get(destinations[i]);
			graph.addFlowWeightedEdge(i * 2, i * 2 + 1, -Integer.MAX_VALUE, 1);
			graph.addFlowWeightedEdge(i * 2 + 1, i * 2 + 2, curPrice, 1);
			graph.addFlowWeightedEdge(i * 2, i * 2 + 2, 0, windowCount);
			for (int j = i + 1; j < count; j++) {
				if (destinations[i].equals(destinations[j])) {
					graph.addFlowWeightedEdge(i * 2 + 1, j * 2, curPrice * 4 / 5, 1);
					break;
				}
			}
		}
		long result = new MinCostFlow(graph, 0, 2 * count, true).minCostMaxFlow(windowCount).first + (long)Integer.MAX_VALUE * count;
		out.printLine(result / 5d);
		int[] index = new int[count + 1];
		Arrays.fill(index, -1);
		NavigableSet<Integer> free = new TreeSet<Integer>();
		for (int i = 0; i < windowCount; i++)
			free.add(i);
		for (int i = 0; i < count; i++) {
			if (index[i] == -1)
				index[i] = free.pollFirst();
			for (int j = graph.firstOutbound(2 * i + 1); j != -1; j = graph.nextOutbound(j)) {
				if (graph.flow(j) != 0) {
					int to = graph.destination(j) >> 1;
					if (index[to] == -1)
						index[to] = index[i];
					else
						free.add(index[i]);
					break;
				}
			}
			out.printLine(index[i] + 1);
		}
    }
}
