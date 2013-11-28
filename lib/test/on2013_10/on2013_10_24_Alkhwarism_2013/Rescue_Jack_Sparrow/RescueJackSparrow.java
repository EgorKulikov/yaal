package on2013_10.on2013_10_24_Alkhwarism_2013.Rescue_Jack_Sparrow;



import net.egork.collections.Pair;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.ShortestDistance;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class RescueJackSparrow {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int fuel = in.readInt();
		long[] danger = IOUtils.readLongArray(in, count);
		int edgeCount = in.readInt();
		int start = in.readInt() - 1;
		int finish = in.readInt() - 1;
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] length = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, length);
		MiscUtils.decreaseByOne(from, to);
		long[] possible = danger.clone();
		Arrays.sort(possible);
		int left = 0;
		int right = count;
		while (left < right) {
			int middle = (left + right) >> 1;
			long level = possible[middle];
			Graph graph = new BidirectionalGraph(count);
			for (int i = 0; i < edgeCount; i++) {
				if (danger[from[i]] <= level && danger[to[i]] <= level)
					graph.addWeightedEdge(from[i], to[i], length[i]);
			}
			Pair<Long,IntList> pair = ShortestDistance.dijkstraAlgorithm(graph, start, finish);
			if (pair != null && pair.first <= fuel)
				right = middle;
			else
				left = middle + 1;
		}
		if (left == count)
			out.printLine(-1);
		else
			out.printLine(possible[left]);
    }
}
