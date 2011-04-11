package net.egork.timus;

import net.egork.collections.Pair;
import net.egork.graph.DFS;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.SimpleEdge;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task1039 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		final int[] conviviality = in.readIntArray(n);
		Graph graph = new Graph(n);
		boolean[] isRoot = new boolean[n];
		Arrays.fill(isRoot, true);
		while (true) {
			int employee = in.readInt() - 1;
			int boss = in.readInt() - 1;
			if (boss == -1)
				break;
			graph.add(new SimpleEdge(boss, employee));
			isRoot[employee] = false;
		}
		DFS<Pair<Integer, Integer>, Object> dfs = new DFS<Pair<Integer, Integer>, Object>(graph) {
			@Override
			protected Pair<Integer, Integer> enterUnvisited(int vertex, Object parameters) {
				return new Pair<Integer, Integer>(Math.max(conviviality[vertex], 0), 0);
			}

			@Override
			protected Pair<Integer, Integer> enterVisited(int vertex, Object parameters) {
				throw new RuntimeException();
			}

			@Override
			protected Object getParameters(int vertex, Pair<Integer, Integer> result, Object parameters, Edge edge, AtomicBoolean enterVertex) {
				return null;
			}

			@Override
			protected Pair<Integer, Integer> processResult(int vertex, Pair<Integer, Integer> result, Object parameters, Pair<Integer, Integer> callResult, AtomicBoolean continueProcess) {
				return new Pair<Integer, Integer>(result.first() + callResult.second(), result.second() + callResult.first());
			}

			@Override
			protected Pair<Integer, Integer> exit(int vertex, Pair<Integer, Integer> result, Object parameters) {
				return new Pair<Integer, Integer>(Math.max(result.first(), result.second()), result.second());
			}
		};
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (isRoot[i])
				result += dfs.run(i, null).first();
		}
		out.println(result);
	}
}

