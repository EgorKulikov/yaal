package on2014_05.on2014_05_17_2014_TopCoder_Open_Algorithm.TreePuzzle;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class TreePuzzle {
    public int[] reachable(int[] parent, int[] token) {
		int count = parent.length;
		Graph graph = BidirectionalGraph.createGraph(count, Arrays.copyOfRange(parent, 1, count),
			Arrays.copyOfRange(ArrayUtils.createOrder(count), 1, count));
		int max = (count + 1) * count * 2;
		int[] queueVertex = new int[max];
		int[] queueLast = new int[max];
		int[] queueToken = new int[max];
		int[] answer = new int[count];
		answer[0] = 1;
		int[][] vertices = new int[count][count];
		ArrayUtils.fill(vertices, -1);
		for (int i = 0; i < count; i++) {
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				int to = graph.destination(j);
				calculate(i, to, vertices, graph);
			}
		}
		int size = 0;
		int totalToken = (int) (ArrayUtils.sumArray(token) - 1);
		boolean[][][] visited = new boolean[count][count][count + 1];
		for (int i = graph.firstOutbound(0); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			int numTokens = numTokens(next, 0, graph, token);
			if (numTokens != vertices[next][0]) {
				visited[next][0][numTokens] = true;
				queueVertex[size] = next;
				queueLast[size] = 0;
				queueToken[size++] = numTokens;
			}
		}
		for (int i = 0; i < size; i++) {
			int current = queueVertex[i];
			int last = queueLast[i];
			int numTokens = queueToken[i];
			answer[current] = 1;
			if (!visited[last][current][totalToken - numTokens]) {
				visited[last][current][totalToken - numTokens] = true;
				queueVertex[size] = last;
				queueLast[size] = current;
				queueToken[size++] = totalToken - numTokens;
			}
			if (numTokens + 1 != vertices[current][last]) {
				for (int j = graph.firstOutbound(current); j != -1; j = graph.nextOutbound(j)) {
					int next = graph.destination(j);
					if (next == last)
						continue;
					int minNewTokens = Math.max(0, numTokens - (vertices[current][last] - 1 - vertices[next][current]));
					int maxNewTokens = Math.min(numTokens, vertices[next][current] - 1);
					for (int k = minNewTokens; k <= maxNewTokens; k++) {
						if (!visited[next][current][k]) {
							visited[next][current][k] = true;
							queueVertex[size] = next;
							queueLast[size] = current;
							queueToken[size++] = k;
						}
					}
				}
			}
		}
		return answer;
    }

	private int numTokens(int vertex, int last, Graph graph, int[] token) {
		int result = token[vertex];
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next != last)
				result += numTokens(next, vertex, graph, token);
		}
		return result;
	}

	private int calculate(int vertex, int last, int[][] vertices, Graph graph) {
		if (vertices[vertex][last] != -1)
			return vertices[vertex][last];
		vertices[vertex][last] = 1;
		for (int j = graph.firstOutbound(vertex); j != -1; j = graph.nextOutbound(j)) {
			int next = graph.destination(j);
			if (next != last)
				vertices[vertex][last] += calculate(next, vertex, vertices, graph);
		}
		return vertices[vertex][last];
	}
}
