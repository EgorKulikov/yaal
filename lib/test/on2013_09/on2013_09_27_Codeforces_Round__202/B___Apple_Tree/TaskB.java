package on2013_09.on2013_09_27_Codeforces_Round__202.B___Apple_Tree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	long[] max;
	long[] lcm;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] apples = IOUtils.readIntArray(in, count);
		int[] from = new int[count - 1];
		int[] to = new int[count - 1];
		IOUtils.readIntArrays(in, from, to);
		MiscUtils.decreaseByOne(from, to);
		Graph graph = BidirectionalGraph.createGraph(count, from, to);
		lcm = new long[count];
		max = new long[count];
		go(0, -1, graph, apples);
		out.printLine(ArrayUtils.sumArray(apples) - max[0]);
    }

	final void go(int vertex, int last, Graph graph, int[] apples) {
		lcm[vertex] = 1;
		max[vertex] = Long.MAX_VALUE;
		int sonCount = 0;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last)
				continue;
			go(next, vertex, graph, apples);
			sonCount++;
			long gcd = IntegerUtils.gcd(lcm[vertex], lcm[next]);
			if (Integer.MAX_VALUE / lcm[vertex] < lcm[next] / gcd) {
				lcm[vertex] = 1;
				max[vertex] = 0;
				break;
			}
			lcm[vertex] *= lcm[next] / gcd;
			max[vertex] = Math.min(max[vertex], max[next]);
		}
		if (sonCount == 0) {
			max[vertex] = apples[vertex];
			return;
		}
		max[vertex] /= lcm[vertex];
		max[vertex] *= lcm[vertex];
		max[vertex] *= sonCount;
		lcm[vertex] *= sonCount;
	}
}
