package net.egork.timus;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.DFS;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.SimpleEdge;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task1045 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int airportCount = in.readInt();
		int startAirport = in.readInt() - 1;
		Graph graph = new BidirectionalGraph(airportCount);
		for (int i = 1; i < airportCount; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			graph.add(new SimpleEdge(from, to));
		}
		final int[] move = new int[airportCount];
		int result = new DFS<Integer, Integer>(graph) {
			@Override
			protected Integer enterUnvisited(int vertex, Integer parameters) {
				return -1;
			}

			@Override
			protected Integer enterVisited(int vertex, Integer parameters) {
				throw new RuntimeException();
			}

			@Override
			protected Integer getParameters(int vertex, Integer result, Integer parameters, Edge edge,
				AtomicBoolean enterVertex)
			{
				int destination = edge.getDestination();
				if (destination == parameters || result != -1 && destination > result) {
					enterVertex.set(false);
					return null;
				}
				move[vertex] = destination;
				return vertex;
			}

			@Override
			protected Integer processResult(int vertex, Integer result, Integer parameters, Integer callResult,
				AtomicBoolean continueProcess)
			{
				if (callResult == -1)
					return move[vertex];
				return result;
			}

			@Override
			protected Integer exit(int vertex, Integer result, Integer parameters) {
				return result;
			}
		}.run(startAirport, -1);
		if (result == -1)
			out.println("First player loses");
		else
			out.println("First player wins flying to airport " + (result + 1));
	}
}

