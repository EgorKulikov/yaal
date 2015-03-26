package on2015_03.on2015_03_17_Single_Round_Match_653.Singing;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.misc.MiscUtils;

public class Singing {
    public int solve(int N, int low, int high, int[] pitch) {
		int[][] qty = new int[N][N];
		MiscUtils.decreaseByOne(pitch);
		low--;
		high--;
		for (int i = 1; i < pitch.length; i++) {
			qty[pitch[i]][pitch[i - 1]]++;
			qty[pitch[i - 1]][pitch[i]]++;
		}
		Graph graph = new Graph(N + 2);
		int source = N;
		int sink = N + 1;
		for (int i = 0; i < low; i++) {
			graph.addFlowEdge(source, i, Integer.MAX_VALUE);
		}
		for (int i = high + 1; i < N; i++) {
			graph.addFlowEdge(i, sink, Integer.MAX_VALUE);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (qty[i][j] != 0 && i != j) {
					graph.addFlowEdge(i, j, qty[i][j]);
				}
			}
		}
		return (int) MaxFlow.dinic(graph, source, sink);
    }
}
