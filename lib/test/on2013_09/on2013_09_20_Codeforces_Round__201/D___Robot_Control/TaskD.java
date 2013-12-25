package on2013_09.on2013_09_20_Codeforces_Round__201.D___Robot_Control;



import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntIterator;
import net.egork.collections.intcollection.IntSet;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = Graph.createGraph(count, to, from);
		int[] degree = new int[count];
		for (int i : from)
			degree[i]++;
		int source = in.readInt() - 1;
		int destination = in.readInt() - 1;
		int[] level = new int[count];
		Arrays.fill(level, -1);
		level[destination] = 0;
		int[] queue = new int[count];
		int size = 1;
		queue[0] = destination;
		for (int i = 0; size != 0; i++) {
			IntSet next = new IntHashSet();
			IntSet thisLevel = new IntHashSet();
			for (int j = 0; j < size; j++) {
				int current = queue[j];
				for (int k = graph.firstOutbound(current); k != -1; k = graph.nextOutbound(k)) {
					int candidate = graph.destination(k);
					if (level[candidate] != -1)
						continue;
					if (--degree[candidate] == 0) {
						queue[size++] = candidate;
						level[candidate] = i;
						thisLevel.add(candidate);
					} else
						next.add(candidate);
				}
			}
			size = 0;
			for (IntIterator iterator = next.iterator(); iterator.isValid(); iterator.advance()) {
				int current = iterator.value();
				if (!thisLevel.contains(current)) {
					level[current] = i + 1;
					queue[size++] = current;
				}
			}
		}
		out.printLine(level[source]);
    }
}
