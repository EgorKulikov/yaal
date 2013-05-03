package on2013_01.on2013_01_25_ACM_ICPC_Amritapuri_onsite_regionals_2012___Replay.Dyslexic_Gollum;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class DyslexicGollum {
	static final int MOD = (int) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int maxPalindrome = in.readInt();
		if (length < maxPalindrome) {
			out.printLine(1 << length);
			return;
		}
		boolean[] forbiddenShort = getForbidden(maxPalindrome);
		boolean[] forbiddenLong = getForbidden(maxPalindrome + 1);
		int[] current = new int[1 << maxPalindrome];
		for (int i = 0; i < current.length; i++)
			current[i] = forbiddenShort[i] ? 0 : 1;
		int[] next = new int[current.length];
		for (int i = maxPalindrome; i < length; i++) {
			Arrays.fill(next, 0);
			for (int j = 0; j < current.length; j++) {
				for (int k = 0; k < 2; k++) {
					int l = j * 2 + k;
					if (forbiddenLong[l])
						continue;
					if (l >= current.length)
						l -= current.length;
					if (forbiddenShort[l])
						continue;
					next[l] += current[j];
					if (next[l] >= MOD)
						next[l] -= MOD;
				}
			}
			int[] temp = current;
			current = next;
			next = temp;
		}
		long answer = ArrayUtils.sumArray(current) % MOD;
		out.printLine(answer);
    }

	private boolean[] getForbidden(int size) {
		boolean[] result = new boolean[1 << size];
		for (int i = 0; i < result.length; i++) {
			boolean isPalindrome = true;
			for (int j = 0, k = size - 1; j < k; j++, k--) {
				if ((i >> j & 1) != (i >> k & 1)) {
					isPalindrome = false;
					break;
				}
			}
			result[i] = isPalindrome;
		}
		return result;
	}
}
