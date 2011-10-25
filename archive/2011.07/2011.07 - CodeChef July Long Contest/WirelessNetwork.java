import net.egork.misc.MiscUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class WirelessNetwork implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int pointCount = in.readInt();
		int computerCount = in.readInt();
		int vertexCount = pointCount + computerCount + 2;
		final int[] graph = new int[vertexCount * vertexCount];
		int source = pointCount + computerCount;
		int sink = pointCount + computerCount + 1;
		final int[] value = new int[vertexCount * vertexCount];
		Queue<Integer> queue = new PriorityQueue<Integer>(pointCount * computerCount, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return value[o1] - value[o2];
			}
		});
		final int[] xPoint = new int[pointCount];
		final int[] yPoint = new int[pointCount];
		for (int i = 0; i < pointCount; i++) {
			xPoint[i] = in.readInt();
			yPoint[i] = in.readInt();
			graph[(computerCount + i) * vertexCount + sink] = in.readInt();
		}
		int answer = 0;
		final int[] intQueue = new int[vertexCount];
		final boolean[] reachable = new boolean[vertexCount];
		final int[] last = new int[vertexCount];
		for (int i = 0; i < computerCount; i++) {
			graph[source * vertexCount + i] = 1;
			int x = in.readInt();
			int y = in.readInt();
			for (int j = 0; j < pointCount; j++) {
				int index = i * vertexCount + computerCount + j;
				value[index] = MiscUtils.distance(x, y, xPoint[j], yPoint[j]);
				queue.add(index);
			}
			Arrays.fill(reachable, false);
			for (int j = 0; j < computerCount; j++) {
				int size = 0;
				if (graph[source * vertexCount + j] != 0) {
					reachable[j] = true;
					last[j] = source;
					intQueue[size++] = j;
				}
				for (int l = 0; l < size; l++) {
					int vertex = intQueue[l];
					int index = vertex * vertexCount;
					if (graph[index + sink] != 0) {
						reachable[sink] = true;
						last[sink] = vertex;
						break;
					}
					int left;
					int right;
					if (vertex < computerCount) {
						left = computerCount;
						right = source;
					} else {
						left = 0;
						right = i + 1;
					}
					for (int k = left; k < right; k++) {
						if (!reachable[k] && graph[index + k] != 0) {
							reachable[k] = true;
							intQueue[size++] = k;
							last[k] = vertex;
						}
					}
				}
			}
			while (!reachable[sink]) {
				int head = queue.poll();
				answer = Math.max(answer, value[head]);
				graph[head]++;
				int from = head / vertexCount;
				int to = head % vertexCount;
				if (reachable[from] && !reachable[to]) {
					intQueue[0] = to;
					reachable[to] = true;
					last[to] = from;
					int size = 1;
					for (int j = 0; !reachable[sink] && j < size; j++) {
						int vertex = intQueue[j];
						int index = vertex * vertexCount;
						if (graph[index + sink] != 0) {
							reachable[sink] = true;
							last[sink] = vertex;
							break;
						}
						int left;
						int right;
						if (vertex < computerCount) {
							left = computerCount;
							right = source;
						} else {
							left = 0;
							right = i + 1;
						}
						for (int k = left; k < right; k++) {
							if (!reachable[k] && graph[index + k] != 0) {
								reachable[k] = true;
								intQueue[size++] = k;
								last[k] = vertex;
								if (graph[k * vertexCount + sink] != 0) {
									last[sink] = k;
									reachable[sink] = true;
									break;
								}
							}
						}
					}
				}
			}
			int current = sink;
			while (current != source) {
				int next = last[current];
				graph[next * vertexCount + current]--;
				graph[current * vertexCount + next]++;
				current = next;
			}
			long result = (long) (Math.sqrt(answer) * 1000 + 0.5);
			out.print(result / 1000);
			result %= 1000;
			out.print(".");
			out.print(result / 100);
			result %= 100;
			out.print(result / 10);
			out.println(result % 10);
//			out.println((result / 1000) + "." + (result / 100 % 10) + (result / 10 % 10) + (result % 10));
//			out.printf("%.3f\n", Math.sqrt(answer));
		}
		out.println();
	}
}

