package on2013_05.on2013_05_30_Codeforces_Round__186.D___Ilya_and_Roads;



import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int companyCount = in.readInt();
		int toFix = in.readInt();
		int[] left = new int[companyCount];
		int[] right = new int[companyCount];
		int[] cost = new int[companyCount];
		IOUtils.readIntArrays(in, left, right, cost);
		MiscUtils.decreaseByOne(left);
		Graph graph = Graph.createGraph(count + 1, left, right);
		int[] best = new int[count + 1];
		Arrays.fill(best, Integer.MAX_VALUE);
		long[][] answer = new long[count + 1][toFix + 1];
		ArrayUtils.fill(answer, Long.MAX_VALUE);
		answer[0][0] = 0;
		for (int i = 0; i < count; i++) {
			for (Edge edge : graph.outbound(i)) {
				int j = edge.getDestination();
				best[j] = Math.min(best[j], cost[edge.getID()]);
			}
			for (int j = 0; j <= toFix; j++) {
				if (answer[i][j] == Long.MAX_VALUE)
					continue;
				answer[i + 1][j] = Math.min(answer[i + 1][j], answer[i][j]);
				for (int k = i + 1; k <= count; k++) {
					if (best[k] != Integer.MAX_VALUE) {
						int l = Math.min(j + k - i, toFix);
						answer[k][l] = Math.min(answer[k][l], answer[i][j] + best[k]);
					}
				}
			}
		}
		if (answer[count][toFix] == Long.MAX_VALUE)
			answer[count][toFix] = -1;
		out.printLine(answer[count][toFix]);
    }
}
