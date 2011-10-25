import net.egork.graph.GraphUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskF implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int answer = 0;
		for (int i = 0; i < count; i++)
			answer += processSpider(in);
		out.println(answer);
	}

	private int processSpider(InputReader in) {
		int size = in.readInt();
		int[] from = new int[size - 1];
		int[] to = new int[size - 1];
		IOUtils.readIntArrays(in, from, to);
		for (int i = 0; i < size - 1; i++) {
			from[i]--;
			to[i]--;
		}
		int[][] graph = GraphUtils.buildSimpleGraph(size, from, to);
		int result = 0;
		for (int i = 0; i < size; i++)
			result = Math.max(result, dfs(i, -1, graph));
		return result;
	}

	private int dfs(int vertex, int last, int[][] graph) {
		int result = 0;
		for (int next : graph[vertex]) {
			if (next != last)
				result = Math.max(result, 1 + dfs(next, vertex, graph));
		}
		return result;
	}
}

