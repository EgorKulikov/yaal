package on2011_11.on2011_10_26.taskc0;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] graph = new int[count][];
		for (int i = 0; i < count; i++)
			graph[i] = IOUtils.readIntArray(in, in.readInt());
		MiscUtils.decreaseByOne(graph);
		int start = 0;
		int[] queue = new int[count];
		int[] distance = new int[count];
		int answer = 0;
		for (int i = 0; i < 3; i++) {
			queue[0] = start;
			int size = 1;
			Arrays.fill(distance, -1);
			distance[start] = 0;
			for (int j = 0; j < size; j++) {
				for (int k : graph[queue[j]]) {
					if (distance[k] == -1) {
						queue[size++] = k;
						distance[k] = distance[queue[j]] + 1;
					}
				}
			}
			start = queue[count - 1];
			answer = 2 * count - 2 - distance[start];
		}
		out.printLine(answer);
	}
}
