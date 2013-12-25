package on2013_03.on2013_03_17_Codeforces_Round__174.C___Coin_Troubles;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	static final int MOD = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int comparisonCount = in.readInt();
		int money = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		int[] more = new int[comparisonCount];
		int[] less = new int[comparisonCount];
		IOUtils.readIntArrays(in, more, less);
		MiscUtils.decreaseByOne(more, less);
		int[] next = new int[count];
		Arrays.fill(next, -1);
		boolean[] start = new boolean[count];
		for (int i = 0; i < comparisonCount; i++) {
			next[more[i]] = less[i];
			start[less[i]] = true;
		}
		for (int i = 0; i < count; i++) {
			if (next[i] != -1 && !start[i]) {
				int current = i;
				while (next[current] != -1) {
					int curNext = next[current];
					money -= values[current];
					if (money < 0) {
						out.printLine(0);
						return;
					}
					values[curNext] += values[current];
					next[current] = -1;
					current = curNext;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			if (next[i] != -1) {
				out.printLine(0);
				return;
			}
		}
		int[] ways = new int[money + 1];
		ways[0] = 1;
		for (int i : values) {
			for (int j = i; j <= money; j++) {
				ways[j] += ways[j - i];
				if (ways[j] >= MOD)
					ways[j] -= MOD;
			}
		}
		out.printLine(ways[money]);
    }
}
