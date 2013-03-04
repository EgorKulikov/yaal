package net.egork;

import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.numbers.Rational;

public class PointyWizardHats {
	public int getNumHats(int[] topHeight, int[] topRadius, int[] bottomHeight, int[] bottomRadius) {
		Graph<String> graph = new Graph<String>();
		for (int i = 0; i < topHeight.length; i++) {
			graph.add(new FlowEdge<String>("source", "top" + i, 1));
			for (int j = 0; j < bottomHeight.length; j++) {
				if (topRadius[i] < bottomRadius[j] && new Rational(topHeight[i], topRadius[i]).compareTo(new Rational(bottomHeight[j], bottomRadius[j])) > 0)
					graph.add(new FlowEdge<String>("top" + i, "bottom" + j, 1));
			}
		}
		for (int i = 0; i < bottomHeight.length; i++)
			graph.add(new FlowEdge<String>("bottom" + i, "sink", 1));
		return (int) GraphAlgorithms.dinic(graph, "source", "sink");
	}


}

