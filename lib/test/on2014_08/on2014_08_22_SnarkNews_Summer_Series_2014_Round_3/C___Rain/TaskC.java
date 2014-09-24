package on2014_08.on2014_08_22_SnarkNews_Summer_Series_2014_Round_3.C___Rain;



import net.egork.graph.Graph;
import net.egork.graph.MaxFlow;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int garageCount = in.readInt();
		int capacity = in.readInt();
		int[] mX = new int[count];
		int[] mY = new int[count];
		IOUtils.readIntArrays(in, mX, mY);
		int[] gX = new int[garageCount];
		int[] gY = new int[garageCount];
		IOUtils.readIntArrays(in, gX, gY);
		double left = 0;
		double right = Math.sqrt(2) * 2000;
		double[][] distance = new double[count][garageCount];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < garageCount; j++) {
				distance[i][j] = Math.hypot(mX[i] - gX[j], mY[i] - gY[j]);
			}
		}
		for (int i = 0; i < 40; i++) {
			double middle = (left + right) / 2;
			Graph graph = new Graph(count + garageCount + 2);
			int source = count + garageCount;
			int sink = source + 1;
			for (int j = 0; j < count; j++) {
				graph.addFlowEdge(source, j, 1);
				for (int k = 0; k < garageCount; k++) {
					if (distance[j][k] < middle) {
						graph.addFlowEdge(j, count + k, 1);
					}
				}
			}
			for (int j = 0; j < garageCount; j++) {
				graph.addFlowEdge(j + count, sink, capacity);
			}
			if (MaxFlow.dinic(graph, source, sink) == count) {
				right = middle;
			} else {
				left = middle;
			}
		}
		out.printLine((left + right) / 2);
    }
}
