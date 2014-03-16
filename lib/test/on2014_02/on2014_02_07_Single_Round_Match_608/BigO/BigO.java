package on2014_02.on2014_02_07_Single_Round_Match_608.BigO;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.StronglyConnectedComponents;

import java.util.Arrays;

public class BigO {
    public int minK(String[] matrix) {
		int count = matrix.length;
		Graph graph = new Graph(count);
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (matrix[i].charAt(j) == 'Y')
					graph.addSimpleEdge(i, j);
			}
		}
		Pair<int[],Graph> kosaraju = StronglyConnectedComponents.kosaraju(graph);
		int[] color = kosaraju.first;
		Graph condensed = kosaraju.second;
		int[] edges = new int[condensed.vertexCount()];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (matrix[i].charAt(j) == 'Y' && color[i] == color[j])
					edges[color[i]]++;
			}
		}
		int[] size = new int[edges.length];
		for (int i = 0; i < count; i++)
			size[color[i]]++;
		for (int i = 0; i < edges.length; i++) {
			if (size[i] < edges[i])
				return -1;
		}
		value = new int[edges.length];
		Arrays.fill(value, -1);
		int answer = 0;
		for (int i = 0; i < edges.length; i++)
			answer = Math.max(answer, dfs(condensed, size, i) - 1);
		return answer;
    }

	int[] value;

	private int dfs(Graph graph, int[] size, int vertex) {
		if (value[vertex] != -1)
			return value[vertex];
		int result = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i))
			result = Math.max(result, dfs(graph, size, graph.destination(i)));
		if (size[vertex] > 1)
			result++;
		return value[vertex] = result;
	}
}
