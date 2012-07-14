import net.egork.collections.map.CPPMap;
import net.egork.graph.Edge;
import net.egork.graph.FlowEdge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskH implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		String[] words = IOUtils.readStringArray(in, count);
		Map<String, Set<Integer>> indices = new CPPMap<String, Set<Integer>>(new Factory<Set<Integer>>() {
			public Set<Integer> create() {
				return new HashSet<Integer>();
			}
		});
		for (int i = 0; i < words.length; i++) {
			StringBuilder builder = new StringBuilder();
			buildAllShorts(words[i], builder, i, 0, indices);
		}
		Graph graph = new Graph(2 + count + indices.size());
		int source = graph.getSize() - 2;
		int sink = graph.getSize() - 1;
		int index = 0;
		String[] shorts = new String[indices.size()];
		for (Map.Entry<String, Set<Integer>> entry : indices.entrySet()) {
			graph.add(new FlowEdge(index + count, sink, 1));
			for (int i : entry.getValue())
				graph.add(new FlowEdge(i, index + count, 1));
			shorts[index++] = entry.getKey();
		}
		for (int i = 0; i < count; i++)
			graph.add(new FlowEdge(source, i, 1));
		if (GraphAlgorithms.dinic(graph, source, sink) != count) {
			out.println(-1);
			return;
		}
		for (int i = 0; i < count; i++) {
			for (Edge edge : graph.getIncident(i)) {
				if (edge.getFlow() != 0) {
					out.println(shorts[edge.getDestination() - count]);
					break;
				}
			}
		}
	}

	private void buildAllShorts(String word, StringBuilder builder, int index, int step,
		Map<String, Set<Integer>> indices)
	{
		int length = builder.length();
		if (step == word.length()) {
			if (length != 0)
				indices.get(builder.toString()).add(index);
			return;
		}
		buildAllShorts(word, builder, index, step + 1, indices);
		if (length != 4) {
			builder.append(word.charAt(step));
			buildAllShorts(word, builder, index, step + 1, indices);
			builder.deleteCharAt(length);
		}
	}
}

