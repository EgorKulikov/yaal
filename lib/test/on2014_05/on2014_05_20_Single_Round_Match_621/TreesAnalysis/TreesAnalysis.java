package on2014_05.on2014_05_20_Single_Round_Match_621.TreesAnalysis;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class TreesAnalysis {
	long answer;
	int totalLeft;
	int totalRight;
	int[] size;
	boolean[] isLeft;
	Graph graph;
	int depth;

    public long treeSimilarity(int[] tree1, int[] tree2) {
		answer = 0;
		int count = tree1.length + 1;
		Graph first = BidirectionalGraph.createGraph(count, tree1, ArrayUtils.createOrder(count - 1));
		Graph second = BidirectionalGraph.createGraph(count, tree2, ArrayUtils.createOrder(count - 1));
		isLeft = new boolean[count];
		size = new int[count];
		calculateSize(0, -1, first, size);
		graph = first;
		for (int i = 0; i < count - 1; i++) {
			Arrays.fill(isLeft, false);
			totalLeft = addAll(i, tree2[i], second, isLeft);
			totalRight = count - totalLeft;
			try {
				calculate(0, -1, totalLeft, totalRight, isLeft, graph, size);
			} catch (Throwable e) {
				System.err.println("Depth = " + depth);
				throw e;
			}
		}
		return answer;
    }

	private int calculate(int current, int last, int totalLeft, int totalRight, boolean[] isLeft, Graph graph, int[] size) {
		int currentLeft = isLeft[current] ? 1 : 0;
		depth++;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				currentLeft += calculate(next, current, totalLeft, totalRight, isLeft, graph, size);
		}
		if (last != -1) {
			int currentRight = size[current] - currentLeft;
			int otherLeft = totalLeft - currentLeft;
			int otherRight = totalRight - currentRight;
			int max = Math.max(Math.max(currentLeft, currentRight), Math.max(otherLeft, otherRight));
			answer += max * max;
		}
		depth--;
		return currentLeft;
	}

	private int calculateSize(int current, int last, Graph graph, int[] size) {
		size[current] = 1;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				size[current] += calculateSize(next, current, graph, size);
		}
		return size[current];
	}

	private int addAll(int current, int last, Graph graph, boolean[] isLeft) {
		isLeft[current] = true;
		int result = 1;
		for (int i = graph.firstOutbound(current); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				result += addAll(next, current, graph, isLeft);
		}
		return result;
	}
}
