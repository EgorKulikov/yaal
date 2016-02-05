package on2016_01.on2016_01_30_World_CodeSprint.Self_Driving_Bus;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SelfDrivingBus {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n - 1];
		int[] b = new int[n - 1];
		IOUtils.readIntArrays(in, a, b);
		MiscUtils.decreaseByOne(a, b);
		Graph graph = BidirectionalGraph.createGraph(n, a, b);
		long answer = 0;
		for (int i = 0; i < n; i++) {
			int size = 0;
			IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(n - i);
			for (int j = i; j < n; j++) {
				size++;
				for (int k = graph.firstOutbound(j); k != -1; k = graph.nextOutbound(k)) {
					int to = graph.destination(k);
					if (to >= i && to < j && setSystem.join(j - i, to - i)) {
						size--;
					}
				}
				if (size == 1) {
					answer++;
				}
			}
		}
		out.printLine(answer);
	}
}
