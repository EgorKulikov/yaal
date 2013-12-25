package on2013_11.on2013_11_19_USACO_2013_November_Contest__Gold.NoChange;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class NoChange {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int purchaseCount = in.readInt();
		int[] coins = IOUtils.readIntArray(in, count);
		int[] prices = IOUtils.readIntArray(in, purchaseCount);
		int[] where = new int[1 << count];
		int answer = -1;
		long[] partial = ArrayUtils.partialSums(prices);
		for (int i = 0; i < (1 << count); i++) {
			if (where[i] == purchaseCount) {
				int current = 0;
				for (int j = 0; j < count; j++) {
					if ((i >> j & 1) == 0)
						current += coins[j];
				}
				answer = Math.max(answer, current);
			}
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 0) {
					int upTo = Arrays.binarySearch(partial, partial[where[i]] + coins[j]);
					if (upTo < 0)
						upTo = -upTo - 2;
					where[i + (1 << j)] = Math.max(where[i + (1 << j)], upTo);
				}
			}
		}
		out.printLine(answer);
    }
}
