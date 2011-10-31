package April2011.UVaHugeEasyContestII;

import net.egork.graph.*;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskV implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int vertexCount = in.readInt();
		int edgeCount = in.readInt();
		int vertex = in.readInt() - 1;
		Graph graph = new BidirectionalGraph(vertexCount);
		for (int i = 0; i < edgeCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			graph.add(new SimpleEdge(from, to));
		}
		final Graph dfsGraph = new Graph(vertexCount);
		new DFS<Object, Integer>(graph) {

			@Override
			protected Object enterUnvisited(int vertex, Integer parameters) {
				if (parameters != -1)
					dfsGraph.add(new SimpleEdge(vertex, parameters));
				return null;
			}

			@Override
			protected Object enterVisited(int vertex, Integer parameters) {
				if (parameters != -1)
					dfsGraph.add(new SimpleEdge(vertex, parameters));
				return null;
			}

			@Override
			protected Integer getParameters(int vertex, Object result, Integer parameters, Edge edge, AtomicBoolean enterVertex) {
				if (edge.getDestination() == parameters) {
					enterVertex.set(false);
					return null;
				}
				return vertex;
			}

			@Override
			protected Object processResult(int vertex, Object result, Integer parameters, Object callResult, AtomicBoolean continueProcess) {
				return null;
			}

			@Override
			protected Object exit(int vertex, Object result, Integer parameters) {
				return null;
			}
		}.run(vertex, -1);
		GraphAlgorithms.DistanceResult distances = GraphAlgorithms.leviteAlgorithm(dfsGraph, vertex);
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < vertexCount; i++) {
			if (i != vertex && distances.getDistances()[i] != Long.MAX_VALUE)
				result.add(i);
		}
		out.print("Case " + testNumber + ": ");
		if (result.isEmpty())
			out.println("none");
		else
			IOUtils.printCollection(SequenceUtils.increment(ListWrapper.wrap(result)), out);
	}
}

