package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	int count;
	int[][] graph;
	int[] size;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		count = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		graph = GraphUtils.buildSimpleGraph(count, from, to);
		long answer = (long)count * (count - 1) * (count - 2) / 6 * (count - 3) / 2;
		size = new int[count];
		answer -= go(0, -1);
		out.printLine(answer);
	}

	private long go(int vertex, int last) {
		long total = 0;
		size[vertex] = 1;
		IntList sizes = new IntArrayList();
		for (int i : graph[vertex]) {
			if (i != last) {
				total += go(i, vertex);
				sizes.add(size[i]);
				size[vertex] += size[i];
			}
		}
		sizes.add(1);
		sizes.add(count - size[vertex]);
		long[] result = new long[5];
		result[0] = 2;
		for (int i : sizes.toArray()) {
			for (int j = 3; j >= 0; j--)
				result[j + 1] += result[j] * i;
		}
		total += result[4];
		return total;
	}
}
