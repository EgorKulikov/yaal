import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Gas implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int cost = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] weight = new int[edgeCount];
		for (int i = 0; i < edgeCount; i++) {
			from[i] = in.readInt() - 1;
			to[i] = in.readInt() - 1;
			weight[i] = in.readInt();
		}
		int[] degree = new int[count];
		for (int i = 0; i < edgeCount; i++) {
			degree[from[i]]++;
			degree[to[i]]++;
		}
		int[][] graph = new int[count][];
		for (int i = 0; i < count; i++)
			graph[i] = new int[degree[i]];
		for (int i = 0; i < edgeCount; i++) {
			graph[from[i]][--degree[from[i]]] = i;
			graph[to[i]][--degree[to[i]]] = i;
		}
		int[] distance = new int[count];
		int[] last = new int[count];
		int[] length = new int[count];
		Arrays.fill(distance, cost);
		distance[0] = 0;
		boolean[] accounted = new boolean[count];
		for (int i = 0; i < count; i++) {
			int min = cost;
			int minIndex = -1;
			for (int j = 0; j < count; j++) {
				if (!accounted[j] && min > distance[j]) {
					min = distance[j];
					minIndex = j;
				}
			}
			if (minIndex == -1)
				break;
			accounted[minIndex] = true;
			for (int j : graph[minIndex]) {
				int other = from[j] + to[j] - minIndex;
				if (distance[other] > distance[minIndex] + weight[j]) {
					distance[other] = distance[minIndex] + weight[j];
					last[other] = j;
					length[other] = length[minIndex] + 1;
				}
			}
		}
		if (distance[count - 1] == cost) {
			out.println("Fair");
			return;
		}
		int[] answer = new int[length[count - 1]];
		int current = count - 1;
		int index = 0;
		while (current != 0) {
			answer[index++] = last[current] + 1;
			current = from[last[current]] + to[last[current]] - current;
		}
		Arrays.sort(answer);
		out.println("Unfair");
		out.println(answer.length);
		out.print(answer[0]);
		for (int i = 1; i < answer.length; i++)
			out.print(" " + answer[i]);
		out.println();
	}
}

