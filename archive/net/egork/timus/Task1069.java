package net.egork.timus;

import net.egork.arrays.ArrayWrapper;
import net.egork.arrays.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.Function;
import net.egork.collections.MultiSet;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.SimpleEdge;
import net.egork.io.IOUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Task1069 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		List<Integer> code = new ArrayList<Integer>();
		try {
			//noinspection InfiniteLoopStatement
			while (true)
				code.add(in.readInt() - 1);
		} catch (InputMismatchException ignored) {
		}
		NavigableSet<Integer> leafs = new TreeSet<Integer>(CollectionUtils.range(0, code.size() - 1));
		MultiSet<Integer> set = new MultiSet<Integer>();
		for (int vertex : code) {
			set.add(vertex);
			leafs.remove(vertex);
		}
		Graph graph = new BidirectionalGraph(code.size() + 1);
		for (int vertex : code) {
			graph.add(new SimpleEdge(vertex, leafs.first()));
			leafs.remove(leafs.first());
			if (set.remove(vertex) == 1)
				leafs.add(vertex);
		}
		for (int i = 0; i < graph.getSize(); i++) {
			out.print((i + 1) + ": ");
			IOUtils.printCollection(ArrayUtils.sort(ArrayWrapper.wrap(Function.apply(graph.getIncident(i), new Function<Edge, Integer>() {
				@Override
				public Integer value(Edge argument) {
					return argument.getDestination() + 1;
				}
			}))), out);
		}
	}
}

