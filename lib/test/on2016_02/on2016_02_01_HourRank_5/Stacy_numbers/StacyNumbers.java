package on2016_02.on2016_02_01_HourRank_5.Stacy_numbers;



import net.egork.collections.set.TreapSet;
import net.egork.numbers.IntegerUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static net.egork.misc.ArrayUtils.*;

public class StacyNumbers {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int q = in.readInt();
		String digits = in.readString();
		int[] order = reversePermutation(StringUtils.suffixArray(digits));
		TreapSet<Integer> set = new TreapSet<>();
		for (int i = 0; i < n; i++) {
			set.add(i);
		}
		long[] value = new long[n + 1];
		for (int i = 0; i < n; i++) {
			value[i + 1] = (value[i] * 10 + digits.charAt(i) - '0') % MOD;
		}
		long[] tens = IntegerUtils.generatePowers(10, n + 1, MOD);
		int length = 1;
		int[] position = reversePermutation(order);
		long lastStart = 0;
		for (int i = 0; i < q; i++) {
			long b = in.readLong() - 1;
			while (b - lastStart >= set.size()) {
				lastStart += set.size();
				if (length == 1) {
					for (int j = 0; j < n; j++) {
						if (digits.charAt(order[j]) == '0') {
							set.remove(j);
						}
					}
				}
				set.remove(position[n - length]);
				length++;
			}
			int left = order[set.get((int) (b - lastStart))];
			int right = left + length;
			long answer = value[right] - value[left] * tens[length];
			answer %= MOD;
			if (answer < 0) {
				answer += MOD;
			}
			out.printLine(answer);
		}
	}
}
