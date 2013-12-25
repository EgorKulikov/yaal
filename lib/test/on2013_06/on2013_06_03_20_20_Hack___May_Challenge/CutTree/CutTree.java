package on2013_06.on2013_06_03_20_20_Hack___May_Challenge.CutTree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CutTree {
	Graph graph;
	long[][] answer;
	long[] temp;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int maxCut = in.readInt();
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		graph = BidirectionalGraph.createGraph(count, from, to);
		answer = new long[count][maxCut + 1];
		temp = new long[maxCut + 1];
		out.printLine(go(0, -1) + 1);
    }

	private long go(int vertex, int last) {
		answer[vertex][0] = 1;
		long result = 0;
		for (Edge edge : graph.outbound(vertex)) {
			int next = edge.getDestination();
			if (next == last)
				continue;
			result += go(next, vertex);
			Arrays.fill(temp, 0);
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j <= i; j++)
					temp[i] += answer[vertex][j] * answer[next][i - j];
			}
			System.arraycopy(temp, 0, answer[vertex], 0, temp.length);
		}
		for (long i : answer[vertex])
			result += i;
		if (last != -1) {
			result -= answer[vertex][answer[vertex].length - 1];
			answer[vertex][1]++;
		}
		return result;
	}
}
