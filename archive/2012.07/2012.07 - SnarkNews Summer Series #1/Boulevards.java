package net.egork;

import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.WeightedEdge;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class Boulevards {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int edgeCount = in.readInt();
        Graph<Integer> graph = new BidirectionalGraph<Integer>();
        int office = in.readInt();
        int firstHome = in.readInt();
        int secondHome = in.readInt();
        for (int i = 0; i < edgeCount; i++) {
            int from = in.readInt();
            int to = in.readInt();
            int length = in.readInt();
            graph.add(new WeightedEdge<Integer>(from, to, length));
        }
        Map<Integer,Long> fromOffice = GraphAlgorithms.dijkstraAlgorithm(graph, office).first;
        Map<Integer,Long> fromFirst = GraphAlgorithms.dijkstraAlgorithm(graph, firstHome).first;
        Map<Integer,Long> fromSecond = GraphAlgorithms.dijkstraAlgorithm(graph, secondHome).first;
        long answer = 0;
        for (int i = 0; i < count; i++) {
            if (fromOffice.get(i) + fromFirst.get(i) == fromOffice.get(firstHome) &&
                    fromOffice.get(i) + fromSecond.get(i) == fromOffice.get(secondHome)) {
                answer = Math.max(answer, fromOffice.get(i));
            }
        }
        out.printLine(answer);
    }
}
