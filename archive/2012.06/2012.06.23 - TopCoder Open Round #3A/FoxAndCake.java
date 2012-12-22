package net.egork;

import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;

import java.util.SortedSet;
import java.util.TreeSet;

public class FoxAndCake {
	public String ableToDivide(int n, int m, int[] x, int[] y) {
		SortedSet<Integer> interestingX = new TreeSet<Integer>();
		SortedSet<Integer> interestingY = new TreeSet<Integer>();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j <= 3; j++) {
				for (int k = 0; k <= j; k++) {
					for (int s1 = -1; s1 <= 1; s1 += 2) {
						for (int s2 = -1; s2 <= 1; s2 += 2) {
							int xx = x[i] + (j - k) * s1;
							int yy = y[i] + k * s2;
							if (xx > 0 && yy > 0 && xx <= n && yy <= m) {
								interestingX.add(xx);
								interestingY.add(yy);
							}
						}
					}
				}
			}
		}
		Graph<Pair<Pair<Integer, Integer>, Boolean>> graph = new Graph<Pair<Pair<Integer, Integer>, Boolean>>();
		for (int i : interestingX) {
			for (int j : interestingY) {
				Pair<Pair<Integer, Integer>, Boolean> key = Pair.makePair(Pair.makePair(i, j), true);
				if (i != interestingX.first())
					graph.add(new FlowEdge<Pair<Pair<Integer, Integer>, Boolean>>(key, Pair.makePair(Pair.makePair(interestingX.headSet(i).last(), j), false), 1));
				if (i != interestingX.last())
					graph.add(new FlowEdge<Pair<Pair<Integer, Integer>, Boolean>>(key, Pair.makePair(Pair.makePair(interestingX.tailSet(i + 1).first(), j), false), 1));
				if (j != interestingY.first())
					graph.add(new FlowEdge<Pair<Pair<Integer, Integer>, Boolean>>(key, Pair.makePair(Pair.makePair(i, interestingY.headSet(j).last()), false), 1));
				if (j != interestingY.last())
					graph.add(new FlowEdge<Pair<Pair<Integer, Integer>, Boolean>>(key, Pair.makePair(Pair.makePair(i, interestingY.tailSet(j + 1).first()), false), 1));
				boolean good = true;
				for (int k = 0; k < 7; k++) {
					if (i == x[k] && j == y[k]) {
						good = false;
						break;
					}
				}
				if (good)
					graph.add(new FlowEdge<Pair<Pair<Integer, Integer>, Boolean>>(Pair.makePair(Pair.makePair(i, j), false), key, 1));
			}
		}
		Pair<Pair<Integer, Integer>, Boolean> source = Pair.makePair(Pair.makePair(0, 0), true);
		Pair<Pair<Integer, Integer>, Boolean> sink = Pair.makePair(Pair.makePair(0, 0), false);
		for (int i = 1; i < 4; i++)
			graph.add(new FlowEdge<Pair<Pair<Integer, Integer>, Boolean>>(source, Pair.makePair(Pair.makePair(x[i], y[i]), true), 1));
		for (int i = 4; i < 7; i++)
			graph.add(new FlowEdge<Pair<Pair<Integer, Integer>, Boolean>>(Pair.makePair(Pair.makePair(x[i], y[i]), false), sink, 1));
		if (GraphAlgorithms.dinic(graph, source, sink) == 3)
			return "Yes";
		else
			return "No";
	}


}

