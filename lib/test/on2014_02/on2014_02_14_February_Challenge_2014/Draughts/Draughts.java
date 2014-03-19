package on2014_02.on2014_02_14_February_Challenge_2014.Draughts;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Draughts {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] hasWindow = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++)
			setSystem.join(from[i], to[i]);
		int[] total = new int[count];
		for (int i = 0; i < count; i++)
			total[setSystem.get(i)] += hasWindow[i];
		long draughtCount = 0;
		for (int i : total)
			draughtCount += (long)i * (i - 1) / 2;
		int[] degree = new int[count];
		for (int i : from)
			degree[i]++;
		for (int i : to)
			degree[i]++;
		int[] queue = new int[count];
		int size = 0;
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		boolean[] removed = new boolean[count];
		for (int i = 0; i < count; i++) {
			if (degree[i] <= 1 - hasWindow[i]) {
				queue[size++] = i;
				removed[i] = true;
			}
		}
		for (int i = 0; i < size; i++) {
			int vertex = queue[i];
			for (int j = graph.firstOutbound(vertex); j != -1; j = graph.nextOutbound(j)) {
				int next = graph.destination(j);
				if (--degree[next] <= 1 - hasWindow[next] && !removed[next]) {
					removed[next] = true;
					queue[size++] = next;
				}
			}
		}
		int hasDraught = ArrayUtils.count(removed, false);
		out.printLine(draughtCount, hasDraught);
    }
}
