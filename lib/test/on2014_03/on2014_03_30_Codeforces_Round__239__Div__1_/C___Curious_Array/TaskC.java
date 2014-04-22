package on2014_03.on2014_03_30_Codeforces_Round__239__Div__1_.C___Curious_Array;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
	private static final int MOD = (int) (1e9 + 7);
	long[] factorial = IntegerUtils.generateFactorial((int) 2e5, MOD);
	long[] reverse = IntegerUtils.generateReverseFactorials((int) 2e5, MOD);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] base = IOUtils.readIntArray(in, count);
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		int[] depth = new int[queryCount];
		IOUtils.readIntArrays(in, from, to, depth);
		MiscUtils.decreaseByOne(from);
		int[] diff = new int[count + 1];
		int[] nDiff = new int[count + 1];
//		int[] delta = new int[count + 1];
		int[] first = new int[101];
		int[] next = new int[queryCount];
		Arrays.fill(first, -1);
		for (int i = 0; i < queryCount; i++) {
			next[i] = first[depth[i]];
			first[depth[i]] = i;
		}
//		int atEnd
		for (int i = 100; i >= 0; i--) {
//			Arrays.fill(delta, 0);
			for (int j = first[i]; j != -1; j = next[j]) {
				diff[from[j]]++;
//				diff[to[j]] -= c(to[j] - from[j] + i, i);
//				if (diff[to[j]] < 0)
//					diff[to[j]] += MOD;
			}
			for (int j = i; j <= 100; j++) {
				for (int k = first[j]; k != -1; k = next[k]) {
					diff[to[k]] -= c(to[k] - from[k] - 1 + j - i, j - i);
					if (diff[to[k]] < 0)
						diff[to[k]] += MOD;
				}
			}
			nDiff[0] = diff[0];
			for (int j = 1; j < count; j++) {
				nDiff[j] = nDiff[j - 1] + diff[j];
				if (nDiff[j] >= MOD)
					nDiff[j] -= MOD;
			}
			int[] temp = diff;
			diff = nDiff;
			nDiff = temp;
		}
		for (int i = 0; i < count; i++) {
			base[i] += diff[i];
			if (base[i] >= MOD)
				base[i] -= MOD;
		}
		out.printLine(base);
    }

	private long c(int a, int b) {
		return factorial[a] * reverse[b] % MOD * reverse[a - b] % MOD;
	}
}
