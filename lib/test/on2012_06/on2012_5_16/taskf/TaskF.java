package on2012_06.on2012_5_16.taskf;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int doorPosition = in.readInt();
		int[] start = new int[count];
		int[] finish = new int[count];
		int[] left = new int[count];
		int[] right = new int[count];
		IOUtils.readIntArrays(in, start, finish, left, right);
		for (int i = 0; i < count; i++)
			finish[i] += start[i];
		int[][] graph = new int[2 * count + 2][2 * count + 2];
		ArrayUtils.fill(graph, -1);
		int[] position = new int[2 * count + 2];
		int[] time = new int[2 * count + 2];
		position[2 * count + 1] = doorPosition;
		time[2 * count + 1] = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			position[2 * i] = left[i];
			position[2 * i + 1] = right[i];
			time[2 * i] = time[2 * i + 1] = finish[i];
		}
		for (int i = 0; i < 2 * count + 1; i++) {
			int maxLeft = Integer.MIN_VALUE;
			for (int j = 0; j < count; j++) {
				if (left[j] < position[i] && finish[j] > time[i]) {
					if (finish[j] - time[i] <= position[i] - right[j])
						continue;
					if (start[j] - time[i] >= position[i] - left[j])
						continue;
					if (start[j] - time[i] <= position[i] - right[j])
						maxLeft = Math.max(maxLeft, right[j]);
					else
						maxLeft = Math.max(maxLeft, position[i] - (start[j] - time[i]));
				}
			}
			int minRight = Integer.MAX_VALUE;
			for (int j = 0; j < count; j++) {
				if (right[j] > position[i] && finish[j] > time[i]) {
					if (finish[j] - time[i] <= left[j] - position[i])
						continue;
					if (start[j] - time[i] >= right[j] - position[i])
						continue;
					if (start[j] - time[i] <= left[j] - position[i])
						minRight = Math.min(minRight, left[j]);
					else
						minRight = Math.min(minRight, position[i] + (start[j] - time[i]));
				}
			}
			for (int j = 0; j < 2 * count + 2; j++) {
				if (position[j] >= maxLeft && position[j] <= minRight && time[i] + Math.abs(position[i] - position[j]) <= time[j])
					graph[i][j] = time[i] + Math.abs(position[i] - position[j]);
			}
		}
		for (int i = 0; i < 2 * count + 1; i++) {
			int lastBad = 0;
			for (int j = 0; j < count; j++) {
				if (position[i] > left[j] && position[i] < right[j] && start[j] < time[i])
					lastBad = Math.max(lastBad, Math.min(finish[j], time[i]));
			}
			for (int j = 0; j < 2 * count + 2; j++) {
				if (graph[j][i] < lastBad)
					graph[j][i] = -1;
			}
		}
		int[] queue = new int[2 * count + 1];
		queue[0] = 2 * count;
		int size = 1;
		int answer = Integer.MAX_VALUE;
		boolean[] visited = new boolean[2 * count + 2];
		visited[2 * count] = true;
		for (int i = 0; i < size; i++) {
			int current = queue[i];
			for (int j = 0; j < 2 * count + 2; j++) {
				if (!visited[j] && graph[current][j] != -1) {
					if (j == 2 * count + 1)
						answer = Math.min(answer, graph[current][j]);
					else {
						queue[size++] = j;
						visited[j] = true;
					}
				}
			}
		}
		if (answer == Integer.MAX_VALUE)
			out.printLine("Impossible");
		else
			out.printLine(answer);
	}
}
