import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaskJ implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		Map<String, Integer> id = new HashMap<String, Integer>();
		String[][] followers = new String[count][];
		String[] name = new String[count];
		for (int i = 0; i < count; i++) {
			id.put(name[i] = in.readString(), i);
			int followerCount = in.readInt();
			followers[i] = new String[followerCount];
			for (int j = 0; j < followerCount; j++)
				followers[i][j] = in.readString();
		}
		String message = in.readLine(false);
		int[][] graph = new int[count][count];
		for (int i = 0; i < count; i++) {
			Arrays.fill(graph[i], 141);
			graph[i][i] = 0;
			for (String follower : followers[i])
				graph[i][id.get(follower)] = name[i].length() + 6;
		}
		int[] distance = new int[count];
		Arrays.fill(distance, 141);
		distance[0] = message.length();
		boolean[] reached = new boolean[count];
		for (int i = 0; i < count; i++) {
			int min = 141;
			int minIndex = -1;
			for (int j = 0; j < count; j++) {
				if (!reached[j] && min > distance[j]) {
					min = distance[j];
					minIndex = j;
				}
			}
			if (minIndex == -1)
				break;
			reached[minIndex] = true;
			for (int j = 0; j < count; j++)
				distance[j] = Math.min(distance[j], distance[minIndex] + graph[minIndex][j]);
		}
		boolean[] know = new boolean[count];
		for (int i = 0; i < count; i++) {
			if (reached[i]) {
				know[i] = true;
				for (String follower : followers[i])
					know[id.get(follower)] = true;
			}
		}
		int result = 0;
		for (int i = 0; i < count; i++) {
			if (know[i])
				result++;
		}
		out.println(result);
		for (int i = 0; i < count; i++) {
			if (know[i])
				out.println(name[i]);
		}
	}
}

