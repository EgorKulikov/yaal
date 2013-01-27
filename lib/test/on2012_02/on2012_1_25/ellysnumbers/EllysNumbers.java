package on2012_02.on2012_1_25.ellysnumbers;



import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.string.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class EllysNumbers {
	public long getSubsets(long n, String[] special) {
		String all = StringUtils.unite(special);
		String[] tokens = all.split(" ");
		int[] multipliers = new int[tokens.length];
		for (int i = 0; i < multipliers.length; i++)
			multipliers[i] = Integer.parseInt(tokens[i]);
		boolean[] isGood = new boolean[multipliers.length];
		boolean containsOne = false;
		for (int i = 0; i < multipliers.length; i++) {
			isGood[i] = n % multipliers[i] == 0 && IntegerUtils.gcd(n / multipliers[i], multipliers[i]) == 1;
			containsOne |= multipliers[i] == 1;
		}
		long nCopy = n;
		for (int i = 0; i < multipliers.length; i++) {
			if (isGood[i])
				nCopy /= IntegerUtils.gcd(nCopy, multipliers[i]);
		}
		if (nCopy != 1)
			return 0;
		Set<Long> primes = new HashSet<Long>();
		for (int i = 0; i < multipliers.length; i++) {
			if (isGood[i]) {
				for (Pair<Long, Integer> pair : IntegerUtils.factorize(multipliers[i])) {
					primes.add(pair.first);
				}
			}
		}
		long[] aPrimes = new long[primes.size()];
		int index = 0;
		for (long prime : primes)
			aPrimes[index++] = prime;
		int[] mask = new int[multipliers.length];
		for (int i = 0; i < multipliers.length; i++) {
			if (isGood[i] && multipliers[i] != 1) {
				int curMask = 0;
				for (int j = 0; j < aPrimes.length; j++) {
					if (multipliers[i] % aPrimes[j] == 0)
						curMask += 1 << j;
				}
				mask[i] = curMask;
			}
		}
		long[] answer = new long[1 << aPrimes.length];
		answer[0] = 1;
		for (int i = 0; i < multipliers.length; i++) {
			if (!isGood[i] || multipliers[i] == 1)
				continue;
			for (int j = answer.length - 1; j > 0; j--) {
				if ((j & mask[i]) == mask[i])
					answer[j] += answer[j - mask[i]];
			}
		}
		return answer[answer.length - 1] * (containsOne ? 2 : 1);
	}

	public static void main(String[] args) {
		int[] primes = IntegerUtils.generatePrimes(50);
		long n = 1;
		for (int prime : primes)
			n *= prime;
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (int i : primes) {
			builder.append(i).append(' ');
			count++;
		}
		for (int i : primes) {
			for (int j : primes) {
				if (i == j)
					break;
				builder.append(i * j).append(' ');
				count++;
			}
		}
		for (int i : primes) {
			for (int j : primes) {
				if (i == j)
					break;
				for (int k : primes) {
					if (k == j)
						break;
					if (count < 500)
						builder.append(i * j * k).append(' ');
					count++;
				}
			}
		}
		long time = System.currentTimeMillis();
		new EllysNumbers().getSubsets(n, new String[]{builder.toString().trim()});
		System.out.println(System.currentTimeMillis() - time);
	}
}

