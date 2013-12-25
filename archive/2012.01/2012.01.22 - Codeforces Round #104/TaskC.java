package net.egork;

import net.egork.collections.map.CPPMap;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TaskC {
	private static final long MOD = 1000000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, length);
		Set<Long> happy = new HashSet<Long>(Array.wrap(IntegerUtils.generateHappy(9)));
		int[] sameHappy = new int[length + 1];
		Map<Long, Integer> happyCount = new CPPMap<Long, Integer>(new Factory<Integer>() {
			public Integer create() {
				return 0;
			}
		});
		for (int i : array) {
			if (happy.contains((long)i))
				happyCount.put((long)i, happyCount.get((long)i) + 1);
			else
				sameHappy[1]++;
		}
		long[] factorial = new long[length + 1];
		long[] reverseFactorial = new long[length + 1];
		factorial[0] = 1;
		reverseFactorial[0] = 1;
		BigInteger bigMod = BigInteger.valueOf(MOD);
		for (int i = 1; i <= length; i++) {
			factorial[i] = (factorial[i - 1] * i) % MOD;
			reverseFactorial[i] = (reverseFactorial[i - 1] * BigInteger.valueOf(i).modInverse(bigMod).longValue()) % MOD;
		}
		long[] result = new long[count + 1];
		for (int i = 0; i <= count; i++) {
			if (sameHappy[1] >= i) {
				result[i] = factorial[sameHappy[1]] * reverseFactorial[i] % MOD * reverseFactorial[sameHappy[1] - i] % MOD;
			}
		}
		long cur = 1;
		for (int l : happyCount.values()) {
			if (cur * (l + 1) > MOD) {
				for (int i = 0; i <= count; i++)
					result[i] %= MOD;
				cur = 1;
			}
			cur *= l + 1;
			for (int i = count; i > 0; i--)
				result[i] += l * result[i - 1];
		}
		out.printLine(result[count] % MOD);
	}
}
