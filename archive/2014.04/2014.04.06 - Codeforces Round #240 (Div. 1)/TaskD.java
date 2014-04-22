package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int volume = in.readInt();
		int money = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		int[] level = new int[count];
		fillLevel(0, -1, 0, graph, level);
		ArrayUtils.sort(level, IntComparator.DEFAULT);
		int start = 1;
		int answer = 1;
		for (int i = 2; i < count; i++) {
			if (level[i] != level[i - 1])
				money -= i - start;
			while (money < 0)
				money += level[i] - level[start++];
			answer = Math.max(answer, i - start + 1);
		}
		answer = Math.min(answer, volume);
		out.printLine(answer);
	}

	private void fillLevel(int vertex, int last, int curLevel, Graph graph, int[] level) {
		level[vertex] = curLevel;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				fillLevel(next, vertex, curLevel + 1, graph, level);
		}
	}
}
