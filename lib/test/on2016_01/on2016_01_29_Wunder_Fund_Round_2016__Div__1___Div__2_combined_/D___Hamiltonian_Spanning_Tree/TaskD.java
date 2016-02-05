package on2016_01.on2016_01_29_Wunder_Fund_Round_2016__Div__1___Div__2_combined_.D___Hamiltonian_Spanning_Tree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	int good;
	int bad;
	Graph graph;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long x = in.readInt();
		long y = in.readInt();
		int[] u = new int[n - 1];
		int[] v = new int[n - 1];
		IOUtils.readIntArrays(in, u, v);
		MiscUtils.decreaseByOne(u, v);
		int[] degree = new int[n];
		for (int i : u) {
			degree[i]++;
		}
		for (int i : v) {
			degree[i]++;
		}
		if (x >= y) {
			if (ArrayUtils.find(degree, n - 1) != -1) {
				out.printLine(x + y * (n - 2));
			} else {
				out.printLine(y * (n - 1));
			}
			return;
		}
		good = n - 1;
		graph = BidirectionalGraph.createGraph(n, u, v);
		go(0, -1);
		out.printLine(good * x + bad * y);
	}

	private boolean go(int vertex, int last) {
		int degree = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			degree++;
			int next = graph.destination(i);
			if (next == last) {
				continue;
			}
			if (go(next, vertex)) {
				degree--;
			}
		}
		if (degree > 2) {
			bad += degree - 2;
			good -= degree - 2;
			return true;
		}
		return false;
	}
}
