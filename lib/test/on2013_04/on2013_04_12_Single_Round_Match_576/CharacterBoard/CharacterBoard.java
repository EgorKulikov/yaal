package on2013_04.on2013_04_12_Single_Round_Match_576.CharacterBoard;



import net.egork.collections.set.EHashSet;
import net.egork.numbers.IntegerUtils;

import java.math.BigInteger;
import java.util.Set;

public class CharacterBoard {
	static final long MOD = (long) (1e9 + 9);

    public int countGenerators(String[] fragment, int W, int i0, int j0) {
		int rowCount = fragment.length;
		int columnCount = fragment[0].length();
		long answer = 0;
		if (W >= rowCount * columnCount)
			answer = (IntegerUtils.power(26, W - rowCount * columnCount + 1, MOD) - 1) * BigInteger.valueOf(25).modInverse(BigInteger.valueOf(MOD)).longValue() % MOD;
		Set<Long> bad = new EHashSet<Long>();
		Set<Long> good = new EHashSet<Long>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				long first = (long)i * W + j;
				for (int k = i; k < rowCount; k++) {
					for (int l = (k == i ? j + 1 : 0); l < columnCount; l++) {
						long second = (long)k * W + l;
						long delta = second - first;
						if (fragment[i].charAt(j) == fragment[k].charAt(l))
							good.add(delta);
						else
							bad.add(delta);
					}
				}
			}
		}
		Set<Long> allBad = new EHashSet<Long>();
		for (long i : bad) {
			for (long j = 1; j * j <= i; j++) {
				if (i % j == 0) {
					allBad.add(j);
					allBad.add(i / j);
				}
			}
		}
		Set<Long> allGood = new EHashSet<Long>();
		for (long i : good) {
			if (allBad.contains(i))
				continue;
			for (long j = 1; j * j <= i; j++) {
				if (i % j == 0) {
					if (!allBad.contains(j))
						allGood.add(j);
					if (!allBad.contains(i / j))
						allGood.add(i / j);
				}
			}
		}
		for (long i : allBad) {
			if (i <= W && i >= rowCount * columnCount)
				answer -= IntegerUtils.power(26, i - rowCount * columnCount, MOD);
		}
		for (long i : allGood) {
			if (i > W)
				continue;
			Set<Long> set = new EHashSet<Long>();
			for (int j = 0; j < rowCount; j++) {
				for (int k = 0; k < columnCount; k++)
					set.add(((long)j * W + k) % i);
			}
			answer += IntegerUtils.power(26, i - set.size(), MOD);
			if (i >= rowCount * columnCount)
				answer -= IntegerUtils.power(26, i - rowCount * columnCount, MOD);
		}
		answer %= MOD;
		answer += MOD;
		answer %= MOD;
		return (int) answer;
    }
}
