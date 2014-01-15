package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int budget = in.readInt();
		int pollution = in.readInt();
		int[][] answer = new int[budget + 1][pollution + 1];
		int[] production = new int[count];
		int[] cost = new int[count];
		int[] harm = new int[count];
		IOUtils.readIntArrays(in, production, cost, harm);
		for (int i = 0; i < count; i++) {
			for (int j = budget; j >= cost[i]; j--) {
				for (int k = pollution; k >= harm[i]; k--)
					answer[j][k] = Math.max(answer[j][k], answer[j - cost[i]][k - harm[i]] + production[i]);
			}
		}
		out.printLine(answer[budget][pollution]);
    }
}
