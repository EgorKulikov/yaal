package on2014_09.on2014_09_21_September_Cook_Off_2014__ACM_ICPC_Warm_up.Updating_Edges_on_Trees;



import net.egork.collections.intervaltree.LCA;
import net.egork.graph.BidirectionalGraph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class UpdatingEdgesOnTrees {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int updateCount = in.readInt();
		int queryCount = in.readInt();
		int[] from = new int[size - 1];
		int[] to = new int[size - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		BidirectionalGraph graph = BidirectionalGraph.createGraph(size, from, to);
		LCA lca = new LCA(graph);
		long[] value = new long[size];
		for (int i = 0; i < updateCount; i++) {
			int start = in.readInt() - 1;
			int end = in.readInt() - 1;
			int excStart = in.readInt() - 1;
			int excEnd = in.readInt() - 1;
			int cLCA = lca.getLCA(start, end);
			value[start]++;
			value[cLCA]--;
			int lsLCA = lca.getLCA(start, excStart);
			int leLCA = lca.getLCA(start, excEnd);
			if (lca.getLevel(lsLCA) < lca.getLevel(cLCA)) {
				lsLCA = cLCA;
			}
			if (lca.getLevel(leLCA) < lca.getLevel(cLCA)) {
				leLCA = cLCA;
			}
			if (lca.getLevel(lsLCA) < lca.getLevel(leLCA)) {
				value[lsLCA]++;
				value[leLCA]--;
			} else {
				value[leLCA]++;
				value[lsLCA]--;
			}
			value[end]++;
			value[cLCA]--;
			int rsLCA = lca.getLCA(end, excStart);
			int reLCA = lca.getLCA(end, excEnd);
			if (lca.getLevel(rsLCA) < lca.getLevel(cLCA)) {
				rsLCA = cLCA;
			}
			if (lca.getLevel(reLCA) < lca.getLevel(cLCA)) {
				reLCA = cLCA;
			}
			if (lca.getLevel(rsLCA) < lca.getLevel(reLCA)) {
				value[rsLCA]++;
				value[reLCA]--;
			} else {
				value[reLCA]++;
				value[rsLCA]--;
			}
		}
		long[] valToPar = new long[size];
		int[] position = new int[size];
		int qSize = 1;
		boolean[] added = new boolean[size];
		added[0] = true;
		for (int i = 0; i < qSize; i++) {
			for (int j = graph.firstOutbound(position[i]); j != -1; j = graph.nextOutbound(j)) {
				if (!added[graph.destination(j)]) {
					position[qSize++] = graph.destination(j);
					added[graph.destination(j)] = true;
				}
			}
		}
		for (int j = size - 1; j >= 0; j--) {
			int i = position[j];
			valToPar[i] += value[i];
			for (int k = graph.firstOutbound(i); k != -1; k = graph.nextOutbound(k)) {
				int vertex = graph.destination(k);
				if (lca.getLCA(i, vertex) == vertex) {
					valToPar[vertex] += valToPar[i];
				}
			}
		}
		if (valToPar[0] != 0) {
			throw new RuntimeException();
		}
		Arrays.fill(value, 0);
		for (int i = 0; i < size; i++) {
			int j = position[i];
			value[j] += valToPar[j];
			for (int k = graph.firstOutbound(j); k != -1; k = graph.nextOutbound(k)) {
				int vertex = graph.destination(k);
				if (lca.getLCA(j, vertex) == j) {
					value[vertex] += value[j];
				}
			}
		}
		for (int i = 0; i < queryCount; i++) {
			int start = in.readInt() - 1;
			int end = in.readInt() - 1;
			long answer = value[start] + value[end] - 2 * value[lca.getLCA(start, end)];
			out.printLine(answer);
		}
    }
}
