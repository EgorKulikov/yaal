package on2016_01.on2016_01_30_World_CodeSprint.Zurikela_s_Graph;



import net.egork.generated.collections.pair.IntIntPair;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ZurikelasGraph {
	Graph graph;
	int[] size;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int q = in.readInt();
		graph = new BidirectionalGraph(q);
		size = new int[q];
		int at = 0;
		for (int i = 0; i < q; i++) {
			char type = in.readCharacter();
			if (type == 'A') {
				int x = in.readInt();
				size[at++] = x;
			} else if (type == 'B') {
				int x = in.readInt() - 1;
				int y = in.readInt() - 1;
				graph.addSimpleEdge(x, y);
			} else {
				int x = in.readInt() - 1;
				size[at++] = go(x, -1).first;
			}
		}
		int answer = 0;
		for (int i = 0; i < q; i++) {
			if (size[i] != 0) {
				answer += go(i, -1).first;
			}
		}
		out.printLine(answer);
	}

	private IntIntPair go(int vertex, int last) {
		int take = size[vertex];
		int notTake = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last) {
				continue;
			}
			IntIntPair result = go(next, vertex);
			take += result.second;
			notTake += result.first;
		}
		take = Math.max(take, notTake);
		size[vertex] = 0;
		return new IntIntPair(take, notTake);
	}
}
