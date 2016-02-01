package on2015_12.on2015_12_08_December_Challenge_2015.Chef_and_Girls;



import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndGirls {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int[] from = new int[m];
		int[] to = new int[m];
		int[] l = new int[m];
		int[] r = new int[m];
		IOUtils.readIntArrays(in, from, to, l, r);
		MiscUtils.decreaseByOne(from, to, l, r);
		Graph graph = Graph.createGraph(n, from, to);
		int[][] cost = new int[32][32];
		ArrayUtils.fill(cost, Integer.MAX_VALUE);
		int[] ql = new int[32];
		int[] qr = new int[32];
		for (int i = 1; i < n; i++) {
			if (graph.firstOutbound(i) != -1) {
				continue;
			}
			int at = i;
			Arrays.fill(ql, 0);
			Arrays.fill(qr, 0);
			while (at != 0) {
				int edge = graph.firstInbound(at);
				if (l[edge] > 0) {
					ql[l[edge] - 1]++;
				}
				if (r[edge] <= 30) {
					qr[r[edge] + 1]++;
				}
				at = graph.source(edge);
			}
			for (int j = 30; j >= 0; j--) {
				ql[j] += ql[j + 1];
			}
			for (int j = 30; j >= 0; j--) {
				ql[j] += ql[j + 1];
			}
			for (int j = 1; j < 32; j++) {
				qr[j] += qr[j - 1];
			}
			for (int j = 1; j < 32; j++) {
				qr[j] += qr[j - 1];
			}
			for (int j = 0; j < 32; j++) {
				for (int k = j; k < 32; k++) {
					cost[j][k] = Math.min(cost[j][k], ql[j] + qr[k]);
				}
			}
		}
		int[] answer = new int[33];
		Arrays.fill(answer, Integer.MAX_VALUE);
		answer[0] = 0;
		for (int i = 1; i <= 32; i++) {
			for (int j = 0; j < i; j++) {
				answer[i] = Math.min(answer[i], answer[j] + cost[j][i - 1]);
			}
		}
		out.printLine(answer[32]);
	}
}
