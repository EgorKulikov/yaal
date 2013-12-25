package on2013_12.on2013_12_15_OpenCup_Peterhof_GP.TaskA;



import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
	List<String> answer = new ArrayList<String>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] start = IOUtils.readIntArray(in, count);
		int[] finish = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(start, finish);
		Graph startGraph = new Graph(count);
		for (int i = 0; i < count; i++) {
			if (start[i] != -1)
				startGraph.addSimpleEdge(start[i], i);
		}
		boolean[] corrupted = new boolean[count];
		for (int i = 0; i < count; i++) {
			if (start[i] != finish[i]) {
				int j = i;
				while (j != -1) {
					corrupted[j] = true;
					j = start[j];
				}
				j = i;
				while (j != -1) {
					corrupted[j] = true;
					j = finish[j];
				}
			}
//			corrupted[i] = start[i] != finish[i];
		}
		for (int i = 0; i < count; i++) {
			if (start[i] == -1)
				dfs(i, startGraph, start, corrupted);
		}
//		Collections.reverse(answer);
		Graph endGraph = new Graph(count);
		for (int i = 0; i < count; i++) {
			if (finish[i] != -1)
				endGraph.addSimpleEdge(finish[i], i);
		}
		for (int i = 0; i < count; i++) {
			if (finish[i] == -1)
				dfs2(i, endGraph, start, finish);
		}
		out.printLine(answer.size());
		for (String s : answer)
			out.printLine(s);
    }

	private void dfs2(int vertex, Graph graph, int[] current, int[] desired) {
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i))
			dfs2(graph.destination(i), graph, current, desired);
		if (current[vertex] == -1 && desired[vertex] != -1)
			answer.add("in " + (vertex + 1) + " " + (desired[vertex] + 1));
	}

	private void dfs(int vertex, Graph graph, int[] current, boolean[] corrupted) {
		if (corrupted[vertex] && current[vertex] != -1) {
			answer.add("out " + (vertex + 1) + " " + (current[vertex] + 1));
			current[vertex] = -1;
		}
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			dfs(graph.destination(i), graph, current, corrupted);
		}
	}
}
