package on2014_02.on2014_02_26_Codeforces_Round__232__Div__1_.D___On_Sum_of_Number_of_Inversions_in_Permutations;



import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.NavigableSet;

public class TaskD {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(permutation);
		long[] qty = new long[count];
		long[] f = new long[count];
		f[0] = 1;
		for (int i = 1; i < count; i++) {
			qty[i] = (f[i - 1] * (((long)i * (i - 1) / 2) % MOD) + i * qty[i - 1]) % MOD;
			f[i] = f[i - 1] * i % MOD;
		}
		long answer = 0;
		long toAdd = 0;
//		NavigableSet<Integer> set = new TreapSet<Integer>();
		int[] fen = new int[count];
		for (int i = 0; i < count; i++) {
			long value = permutation[i];
			for (int j = permutation[i]; j >= 0; j = (j & (j + 1)) - 1)
				value -= fen[j];
//			int at = permutation[i];
//			set.add(permutation[i]);
			for (int j = permutation[i]; j < count; j = (j | (j + 1)))
				fen[j]++;
			int remaining = count - i - 1;
			answer += (f[remaining] * ((value * (value - 1)) / 2 % MOD) + value * qty[remaining] + (value * f[remaining] % MOD) * toAdd) % MOD;
			toAdd += value;
			toAdd %= MOD;
		}
		answer += toAdd;
		out.printLine(answer % MOD);
    }
}
