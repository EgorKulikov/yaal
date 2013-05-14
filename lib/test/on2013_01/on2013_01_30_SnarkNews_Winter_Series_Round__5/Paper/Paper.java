package on2013_01.on2013_01_30_SnarkNews_Winter_Series_Round__5.Paper;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Paper {
	long[] graph;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxEliminate = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		int[] degree = new int[count];
		for (int i = 0; i < edgeCount; i++) {
			degree[from[i]]--;
			degree[to[i]]--;
		}
//		int[] order = ArrayUtils.order(degree);
//		int[] index = ArrayUtils.reversePermutation(order);
//		for (int i = 0; i < edgeCount; i++) {
//			from[i] = index[from[i]];
//			to[i] = index[to[i]];
//		}
		for (int i = 0; i < edgeCount; i++) {
			if (from[i] > to[i]) {
				int temp = from[i];
				from[i] = to[i];
				to[i] = temp;
			}
		}
		graph = new long[count];
		for (int i = 0; i < edgeCount; i++)
			graph[from[i]] |= 1L << to[i];
		int result = go(0, maxEliminate, (1L << count) - 1);
		if (result >= 0)
			out.printLine(maxEliminate - result);
		else
			out.printLine("IMPOSSIBLE");
	}

	private int go(int step, int remaining, long mask) {
		if (step == graph.length)
			return remaining;
		if ((mask >> step & 1) == 0)
			return go(step + 1, remaining, mask);
		int outDegree = Long.bitCount(graph[step] & mask);
		if (outDegree == 0)
			return go(step + 1, remaining, mask - (1L << step));
		if (remaining == 0)
			return -1;
		if (outDegree == 1)
			return go(step + 1, remaining - 1, mask & (~graph[step]));
		int result = go(step + 1, remaining - 1, mask - (1L << step));
		if (remaining >= outDegree)
			result = Math.max(result, go(step + 1, remaining - outDegree, mask & (~graph[step])));
		return result;
	}
}
