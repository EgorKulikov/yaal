package on2014_06.on2014_06_01_Codeforces_Round__250__Div__1_.B___The_Child_and_Zoo;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	int current;
	double answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] qty = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		long[] size = new long[count];
		Arrays.fill(size, 1);
		answer = 0;
		setSystem.setListener(new IndependentSetSystem.Listener() {
			@Override
			public void joined(int joinedRoot, int root) {
				answer += size[joinedRoot] * size[root] * current;
				size[root] += size[joinedRoot];
			}
		});
		int[] order = ArrayUtils.order(qty);
		ArrayUtils.reverse(order);
		boolean[] added = new boolean[count];
		for (int i : order) {
			added[i] = true;
			current = qty[i];
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				int destination = graph.destination(j);
				if (added[destination])
					setSystem.join(i, destination);
			}
		}
		answer *= 2;
		answer /= count;
		answer /= count - 1;
		out.printLine(answer);
    }
}
