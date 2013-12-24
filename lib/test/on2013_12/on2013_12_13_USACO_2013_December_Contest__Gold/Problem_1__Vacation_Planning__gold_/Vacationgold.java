package on2013_12.on2013_12_13_USACO_2013_December_Contest__Gold.Problem_1__Vacation_Planning__gold_;



import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Vacationgold {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int hubCount = in.readInt();
		int queryCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] cost = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, cost);
		int[] hubs = IOUtils.readIntArray(in, hubCount);
		MiscUtils.decreaseByOne(from, to, hubs);
		Arrays.sort(hubs);
		int[] hubId = new int[count];
		Arrays.fill(hubId, -1);
		for (int i = 0; i < hubCount; i++)
			hubId[hubs[i]] = i;
		Graph graph = Graph.createWeightedGraph(count, from, to, ArrayUtils.asLong(cost));
		long[][] minDistance = new long[hubCount][hubCount];
		ArrayUtils.fill(minDistance, Long.MAX_VALUE / 2);
		for (int i : hubs) {
			int at = hubId[i];
			minDistance[at][at] = 0;
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				int nAt = hubId[next];
				if (nAt >= 0)
					minDistance[at][nAt] = Math.min(minDistance[at][nAt], graph.weight(j));
				else {
					for (int k = graph.firstOutbound(next); k != -1; k = graph.nextOutbound(k)) {
						int last = graph.destination(k);
						int lAt = hubId[last];
						minDistance[at][lAt] = Math.min(minDistance[at][lAt], graph.weight(j) + graph.weight(k));
					}
				}
			}
		}
		for (int i = 0; i < hubCount; i++) {
			for (int j = 0; j < hubCount; j++) {
				for (int k = 0; k < hubCount; k++)
					minDistance[j][k] = Math.min(minDistance[j][k], minDistance[j][i] + minDistance[i][k]);
			}
		}
		long[][] distances = new long[hubCount][count];
		ArrayUtils.fill(distances, Long.MAX_VALUE / 2);
		for (int i = 0; i < hubCount; i++) {
			for (int j = graph.firstOutbound(hubs[i]); j != -1; j = graph.nextOutbound(j)) {
				int destination = graph.destination(j);
				for (int k = 0; k < hubCount; k++)
					distances[k][destination] = Math.min(distances[k][destination], minDistance[k][i] + graph.weight(j));
			}
			for (int j = 0; j < hubCount; j++) {
				distances[i][hubs[j]] = Math.min(distances[i][hubs[j]], minDistance[i][j]);
			}
		}
		int served = 0;
		long total = 0;
		for (int i = 0; i < queryCount; i++) {
			int start = in.readInt() - 1;
			int finish = in.readInt() - 1;
			int sAt = hubId[start];
			if (sAt >= 0) {
				long current = distances[sAt][finish];
				if (current < Long.MAX_VALUE / 2) {
					served++;
					total += current;
				}
			} else {
				long current = Long.MAX_VALUE / 2;
				for (int j = graph.firstOutbound(start); j != -1; j = graph.nextOutbound(j)) {
					int at = hubId[graph.destination(j)];
					current = Math.min(current, graph.weight(j) + distances[at][finish]);
				}
				if (current < Long.MAX_VALUE / 2) {
					served++;
					total += current;
				}
			}
		}
		out.printLine(served);
		out.printLine(total);
    }
}
