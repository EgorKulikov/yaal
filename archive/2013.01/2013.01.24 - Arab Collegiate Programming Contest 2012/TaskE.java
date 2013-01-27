package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Graph<Object> graph = new Graph<Object>();
		Object source = new Object();
		Object sink = new Object();
		char[] original = in.readString().toCharArray();
		int[] qty = new int[26];
		for (char c : original) {
			graph.addFlowEdge(c, sink, 1);
			qty[c - 'a']++;
		}
		char[][] moves = new char[original.length][];
		for (int i = 0; i < original.length; i++) {
			moves[i] = in.readString().toCharArray();
			for (char c : moves[i])
				graph.addFlowEdge(i, c, 1);
			Arrays.sort(moves[i]);
			graph.addFlowEdge(source, i, 1);
		}
		if (MaxFlow.dinic(graph, source, sink) != original.length) {
			out.printLine("NO SOLUTION");
			return;
		}
		char[] answer = new char[original.length];
		for (int i = 0; i < original.length; i++) {
			for (char c : moves[i]) {
				if (qty[c - 'a'] == 0)
					continue;
				graph = new Graph<Object>();
				qty[c - 'a']--;
				for (int j = 0; j < 26; j++) {
					if (qty[j] != 0)
						graph.addFlowEdge((char)(j + 'a'), sink, qty[j]);
				}
				for (int j = i + 1; j < original.length; j++) {
					graph.addFlowEdge(source, j, 1);
					for (char cc : moves[j])
						graph.addFlowEdge(j, cc, 1);
				}
				if (i == original.length - 1 || MaxFlow.dinic(graph, source, sink) == original.length - i - 1) {
					answer[i] = c;
					break;
				}
				qty[c - 'a']++;
			}
		}
		out.printLine(answer);
    }
}
