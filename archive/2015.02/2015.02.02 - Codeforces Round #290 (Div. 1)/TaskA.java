package net.egork;

import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[] names = IOUtils.readStringArray(in, count);
		Graph graph = new Graph(26);
		for (int i = 1; i < names.length; i++) {
			for (int j = 0; j < names[i - 1].length(); j++) {
				if (j == names[i].length()) {
					out.printLine("Impossible");
					return;
				}
				if (names[i - 1].charAt(j) != names[i].charAt(j)) {
					graph.addSimpleEdge(names[i - 1].charAt(j) - 'a', names[i].charAt(j) - 'a');
					break;
				}
			}
		}
		int[] result = GraphAlgorithms.topologicalSort(graph);
		if (result == null) {
			out.printLine("Impossible");
			return;
		}
		for (int i : result) {
			out.print((char)('a' + i));
		}
		out.printLine();
	}
}
