package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GiftsAtOlympics {
	private static final long MOD = (long) (1e9 + 7);
	private long[] factorials;
	private long[] reverseFactorials;

	long c(int n, int k) {
		return factorials[n] * reverseFactorials[k] % MOD * reverseFactorials[n - k] % MOD;
	}

	long reverseC(int n, int k) {
		return reverseFactorials[n] * factorials[k] % MOD * factorials[n - k] % MOD;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] size = IOUtils.readIntArray(in, count);
		int sum = (int) ArrayUtils.sumArray(size);
		factorials = IntegerUtils.generateFactorial(sum + 1, MOD);
		reverseFactorials = IntegerUtils.generateReverseFactorials(sum + 1, MOD);
		int max = CollectionUtils.maxElement(Array.wrap(size));
		long[] answer = new long[max + 1];
		long[] symmetricAnswer = new long[max + 1];
		Set<Integer> viable = new HashSet<Integer>();
		for (int i = 0; i < count; i++) {
			if (size[i] != 1)
			viable.add(i);
		}
		sum = 0;
		answer[1] = 1;
		int symmetricSum = 0;
		symmetricAnswer[1] = 1;
		int odd = 0;
		int sumOdd = 0;
		for (int i = 0; i < count; i++) {
			answer[1] = answer[1] * c(sum += size[i], size[i]) % MOD;
			if ((size[i] & 1) == 0)
				symmetricAnswer[1] = symmetricAnswer[1] * c(symmetricSum += (size[i] >> 1), size[i] >> 1) % MOD;
			else {
				odd++;
				sumOdd += size[i];
			}
		}
		long lastSymmetric = symmetricAnswer[1];
		if (odd > 1)
			symmetricAnswer[1] = 0;
		else if (odd == 1)
			symmetricAnswer[1] = symmetricAnswer[1] * c(symmetricSum + (sumOdd >> 1), sumOdd >> 1) % MOD;
		for (int i = 2; i <= max; i++) {
			answer[i] = answer[i - 1];
			symmetricAnswer[i] = lastSymmetric;
			Iterator<Integer> iterator = viable.iterator();
			while (iterator.hasNext()) {
				int index = iterator.next();
				int was = (size[index] - 1) / (i - 1) + 1;
				answer[i] = answer[i] * reverseC(sum, was) % MOD;
				sum -= was;
				if ((was & 1) == 0) {
					symmetricAnswer[i] = symmetricAnswer[i] * reverseC(symmetricSum, was >> 1) % MOD;
					symmetricSum -= was >> 1;
				} else {
					odd--;
					sumOdd -= was;
				}
				int will = (size[index] - 1) / i + 1;
				answer[i] = answer[i] * c(sum += will, will) % MOD;
				if (size[index] == i)
					iterator.remove();
				if ((will & 1) == 0) {
					symmetricAnswer[i] = symmetricAnswer[i] * c(symmetricSum + (will >> 1), will >> 1) % MOD;
					symmetricSum += will >> 1;
				} else {
					odd++;
					sumOdd += will;
				}
			}
			lastSymmetric = symmetricAnswer[i];
			if (odd > 1)
				symmetricAnswer[i] = 0;
			else if (odd == 1)
				symmetricAnswer[i] = symmetricAnswer[i] * c(symmetricSum + (sumOdd >> 1), sumOdd >> 1) % MOD;
		}
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int index = Math.min(max, in.readInt());
			out.printLine((answer[index] + symmetricAnswer[index]) * (MOD + 1) / 2 % MOD);
		}
	}
}
