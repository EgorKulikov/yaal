package on2013_07.on2013_07_17_Yandex_Algorithm_Onlne_Round__2.TaskE;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	int answer;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		boolean[] inTree = new boolean[edgeCount];
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
		for (int i = 0; i < edgeCount; i++)
			inTree[i] = setSystem.join(graph.source(i << 1), graph.destination(i << 1));
		int[] level = new int[count];
		countLevel(0, -1, 0, graph, inTree, level);
		solve(0, -1, graph, inTree, level);
		out.printLine(answer);
    }

	private int solve(int vertex, int last, Graph graph, boolean[] inTree, int[] level) {
		int good = 0;
		int superGood = 0;
		boolean badSelf = false;
		boolean badSelfAndParent = false;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last)
				continue;
			if (inTree[i >> 1]) {
				int result = solve(next, vertex, graph, inTree, level);
				if (result == -1)
					badSelf = true;
				if (result == 1)
					good++;
				if (result == 2)
					superGood++;
			} else {
				if (level[next] <= level[vertex])
					badSelf = true;
				if (level[next] < level[vertex])
					badSelfAndParent = true;
			}
		}
		answer += superGood;
		answer += good / 2;
		good %= 2;
		if (badSelfAndParent)
			return -1;
		if (badSelf)
			return 0;
		return good + 1;
	}

	private void countLevel(int vertex, int last, int curLevel, Graph graph, boolean[] inTree, int[] level) {
		level[vertex] = curLevel;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			if (inTree[i >> 1] && graph.destination(i) != last)
				countLevel(graph.destination(i), vertex, curLevel + 1, graph, inTree, level);
		}
	}
}
