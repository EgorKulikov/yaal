package net.egork;

import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	static final long MOD = 1000002013;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long stationCost = in.readInt();
		int count = in.readInt();
		int[] from = new int[count];
		int[] to = new int[count];
		int[] qty = new int[count];
		IOUtils.readIntArrays(in, from, to, qty);
		long realCost = 0;
		for (int i = 0; i < count; i++) {
			long length = to[i] - from[i];
			realCost += calculateCost(length, stationCost, qty[i]);
		}
		realCost %= MOD;
		int[] interestingPoints = ArrayUtils.compress(from, to);
		int[] orderFrom = ArrayUtils.order(from);
		int[] orderTo = ArrayUtils.order(to);
		int[] indexStack = new int[count];
		int[] qtyStack = new int[count];
		int j = 0;
		int k = 0;
		int l = 0;
		long cheatCost = 0;
		for (int i = 0; i < interestingPoints.length; i++) {
			while (l < count && from[orderFrom[l]] == i) {
				indexStack[j] = interestingPoints[i];
				qtyStack[j++] = qty[orderFrom[l++]];
			}
			while (k < count && to[orderTo[k]] == i) {
				int remaining = qty[orderTo[k++]];
				while (remaining != 0) {
					int current = Math.min(remaining, qtyStack[j - 1]);
					remaining -= current;
					qtyStack[j - 1] -= current;
					cheatCost += calculateCost(interestingPoints[i] - indexStack[j - 1], stationCost, current);
					if (qtyStack[j - 1] == 0)
						j--;
				}
			}
		}
		cheatCost %= MOD;
		long answer = realCost - cheatCost;
		if (answer < 0)
			answer += MOD;
		out.printLine("Case #" + testNumber + ":", answer);
    }

	private long calculateCost(long length, long stationCost, int qty) {
		long current = (length * stationCost - length * (length - 1) / 2) % MOD;
		return current * qty % MOD;
	}


}
