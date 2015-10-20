package on2015_01.on2015_01_21_SnarkNews_Winter_Series_2015__Round_3.TaskB;



import net.egork.graph.Graph;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		int[] salary = new int[count];
		Graph graph = new Graph(count);
		int[] degree = new int[count];
		for (int i = 0; i < count; i++) {
			salary[i] = in.readInt();
			degree[i] = in.readInt();
			for (int j = 0; j < degree[i]; j++) graph.addSimpleEdge(in.readInt(), i);
		}
		int[] queue = new int[count];
		int[] degreeCopy = new int[count];
		int answer = Integer.MAX_VALUE;
		int at = -1;
		for (int i = 0; i < count; i++) {
			queue[0] = i;
			int size = 1;
			int total = 0;
			System.arraycopy(degree, 0, degreeCopy, 0, count);
			for (int j = 0; j < size; j++) {
				int current = queue[j];
				total += salary[current];
				for (int k = graph.firstOutbound(current); k != -1; k = graph.nextOutbound(k)) {
					int next = graph.destination(k);
					if (--degreeCopy[next] == 0) queue[size++] = next;
				}
			}
			if (total >= required && total <= answer) {
				answer = total;
				at = i;
			}
		}
		out.printLine(at);
	}
}
