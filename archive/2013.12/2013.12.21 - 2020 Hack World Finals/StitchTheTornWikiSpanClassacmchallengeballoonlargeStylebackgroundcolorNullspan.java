package net.egork;

import net.egork.collections.map.Counter;
import net.egork.graph.Graph;
import net.egork.graph.MinCostFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class StitchTheTornWikiSpanClassacmchallengeballoonlargeStylebackgroundcolorNullspan {
	int[] add = {5000, 500, 50, 5};

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		String[][] first = new String[count][];
		String[][] second = new String[count][];
		for (int i = 0; i < count; i++) {
			first[i] = in.readLine().split("[^A-Za-z0-9]");
			for (int j = 0; j < first[i].length; j++)
				first[i][j] = first[i][j];
		}
		in.readLine();
		for (int i = 0; i < count; i++) {
			second[i] = in.readLine().split("[^A-Za-z0-9]");
			for (int j = 0; j < second[i].length; j++)
				second[i][j] = second[i][j];
		}
		Counter<String>[] sets = new Counter[count];
		for (int i = 0; i < count; i++) {
			sets[i] = new Counter<String>();
			String[] strings = first[i];
			for (int i1 = 0; i1 < strings.length; i1++) {
				String s = strings[i1];
				if (!s.isEmpty() && Character.isUpperCase(s.charAt(0)) && !sets[i].containsKey(s.toLowerCase())) {
					if (i1 < add.length)
						sets[i].add(s.toLowerCase(), add[i1]);
					else
						sets[i].add(s.toLowerCase());
				}
			}
			for (String s : strings) {
				if (s.equals(s.toLowerCase()))
					sets[i].remove(s);
			}
			sets[i].remove("the");
			sets[i].remove("a");
		}
		long[][] score = new long[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (String s : second[j]) {
					if (sets[i].containsKey(s.toLowerCase()) && !s.equals(s.toLowerCase()))
						score[i][j] += sets[i].get(s.toLowerCase());
				}
			}
		}
		Graph graph = new Graph(2 * count + 2);
		for (int i = 0; i < count; i++) {
			graph.addFlowEdge(2 * count, i, 1);
			graph.addFlowEdge(i + count, 2 * count + 1, 1);
			for (int j = 0; j < count; j++)
				graph.addFlowWeightedEdge(i, j + count, -score[i][j], 1);
		}
		new MinCostFlow(graph, 2 * count, 2 * count + 1, true).minCostMaxFlow();
		for (int i = 0; i < count; i++) {
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				if (graph.flow(j) > 0) {
					out.printLine(graph.destination(j) - count + 1);
					break;
				}
			}
		}
    }
}
