package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int edgeCount = count * (count - 1) / 2;
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] distance = new int[edgeCount];
		int k = 0;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				from[k] = i;
				to[k] = j;
				int dx = x[i] - x[j];
				int dy = y[i] - y[j];
				distance[k++] = dx * dx + dy * dy;
			}
		}
		System.err.println("Start sort");
		int[] order = ArrayUtils.order(distance);
		System.err.println("End sort");
		ArrayUtils.reverse(order);
		int[][] graph = new int[count][(count + 31) >> 5];
		for (int i : order) {
			int a = from[i];
			int b = to[i];
			int[] ga = graph[a];
			int[] gb = graph[b];
			for (int j = 0; j < ga.length; j++) {
				if ((ga[j] & gb[j]) != 0) {
					out.printLine(Math.sqrt(distance[i]) / 2);
					return;
				}
			}
			ga[b >> 5] |= 1 << (b & 31);
			gb[a >> 5] |= 1 << (a & 31);
		}
		throw new RuntimeException();
    }
}
