package on2014_11.on2014_11_15_Codeforces_Round__277_5__Div__2_.TaskD;



import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = Graph.createGraph(count, from, to);
		int[][] result = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = graph.firstOutbound(i); j != -1; j = graph.nextOutbound(j)) {
				for (int k = graph.firstInbound(i); k != -1; k = graph.nextInbound(k)) {
					result[graph.destination(j)][graph.source(k)]++;
				}
			}
		}
		long answer = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i != j) {
					answer += result[i][j] * (result[i][j] - 1);
				}
			}
		}
		answer /= 2;
		out.printLine(answer);
    }
}
