package net.egork;

import net.egork.collections.Pair;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class River {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		Graph<Object> graph = new Graph<Object>();
		int count = in.readInt();
		Set<String> sigars = new HashSet<String>();
		Set<String> alcohol = new HashSet<String>();
		Set<String> fish = new HashSet<String>();
		Object source = "source";
		Object sink = "sink";
		int answer = count;
		for (int i = 0; i < count; i++) {
			Object currentSigar = "sugar" + i;
			Object currentFish = "fish" + i;
			Object currentAlcohol = "alcohol" + i;
			graph.add(new FlowEdge<Object>(currentSigar, sink, 1));
			graph.add(new FlowEdge<Object>(currentFish, sink, 1));
			graph.add(new FlowEdge<Object>(currentAlcohol, sink, 1));
			int countSigars = in.readInt();
			for (int j = 0; j < countSigars; j++) {
				String sort = in.readString();
				if (!sigars.contains(sort))
					graph.add(new FlowEdge<Object>(source, Pair.makePair(sort, 0), 2));
				sigars.add(sort);
				graph.add(new FlowEdge<Object>(Pair.makePair(sort, 0), currentSigar, 1));
			}
			int countAlcohol = in.readInt();
			for (int j = 0; j < countAlcohol; j++) {
				String sort = in.readString();
				if (!alcohol.contains(sort))
					graph.add(new FlowEdge<Object>(source, Pair.makePair(sort, 1), 2));
				alcohol.add(sort);
				graph.add(new FlowEdge<Object>(Pair.makePair(sort, 1), currentAlcohol, 1));
			}
			int countFish = in.readInt();
			for (int j = 0; j < countFish; j++) {
				String sort = in.readString();
				if (!fish.contains(sort))
					graph.add(new FlowEdge<Object>(source, Pair.makePair(sort, 2), 2));
				fish.add(sort);
				graph.add(new FlowEdge<Object>(Pair.makePair(sort, 2), currentFish, 1));
			}
			if (answer != count)
				continue;
			if (GraphAlgorithms.dinic(graph, source, sink) != 3) {
				answer = i;
			}
		}
		out.printLine(answer);
	}
}
