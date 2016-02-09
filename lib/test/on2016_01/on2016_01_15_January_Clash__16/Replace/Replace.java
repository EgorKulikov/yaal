package on2016_01.on2016_01_15_January_Clash__16.Replace;



import net.egork.collections.map.Counter;
import net.egork.collections.map.Indexer;
import net.egork.generated.collections.pair.LongLongPair;
import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Replace {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String s = in.readString();
		s = s.substring(1, s.length() - 1);
		Indexer<List<Integer>> present = new Indexer<>();
		Set<LongLongPair> edges = new HashSet<>();
		Counter<Integer> inS = new Counter<>();
		Stack<List<Integer>> children = new Stack<>();
		children.add(new ArrayList<Integer>());
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '{') {
				children.add(new ArrayList<Integer>());
			} else {
				List<Integer> current = children.pop();
				Collections.sort(current);
				if (!present.containsKey(current)) {
					int id = present.get(current);
					for (int l : current) {
						edges.add(new LongLongPair(id, l));
					}
				}
				children.peek().add(present.get(current));
			}
		}
		for (int l : children.pop()) {
			inS.add(l);
		}
		Graph graph = new Graph(present.size() + 2);
		for (LongLongPair p : edges) {
			graph.addFlowEdge((int)p.first, (int)p.second, Integer.MAX_VALUE);
		}
		for (int l = 0; l < present.size(); l++) {
			if (inS.containsKey(l)) {
				graph.addFlowEdge(present.size(), l, inS.get(l));
			} else {
				graph.addFlowEdge(l, present.size() + 1, 1);
			}
		}
		out.printLine(MaxFlow.dinic(graph, present.size(), present.size() + 1));
	}
}
