package net.egork;

import net.egork.collections.comparators.IntComparator;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;

public class LucyAndTheFlowers {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String sample = in.readString();
		SimpleStringHash hash = new SimpleStringHash(sample);
		SimpleStringHash reverse = new SimpleStringHash(StringUtils.reverse(sample));
		int[] order = StringUtils.suffixArray(sample);
		int[] maxLength = new int[sample.length()];
		for (int i = 0; i < sample.length(); i++) {
			int left = 1;
			int right = Math.min(i + 1, sample.length() - i);
			int reversePosition = sample.length() - i - 1;
			while (left < right) {
				int middle = (left + right + 1) >> 1;
				if (hash.hash(i, i + middle) == reverse.hash(reversePosition, reversePosition + middle))
					left = middle;
				else
					right = middle - 1;
			}
			maxLength[i] = left;
		}
		long bouquets = calculate(maxLength, hash, sample, order);
		for (int i = 0; i < sample.length(); i++) {
			int left = 0;
			int right = Math.min(i, sample.length() - i);
			int reversePosition = sample.length() - i;
			while (left < right) {
				int middle = (left + right + 1) >> 1;
				if (hash.hash(i, i + middle) == reverse.hash(reversePosition, reversePosition + middle))
					left = middle;
				else
					right = middle - 1;
			}
			maxLength[i] = left;
		}
		bouquets += calculate(maxLength, hash, sample, order);
		int count = in.readInt();
		if (count == 1) {
			out.printLine(bouquets);
			return;
		}
		BigInteger lBouquets = BigInteger.valueOf(bouquets);
		if (count == 2) {
			BigInteger result = lBouquets;
			result = result.multiply(result.add(BigInteger.ONE)).shiftRight(1);
			out.printLine(result);
			return;
		}
		int[] answer = new int[count + 1];
		for (int i = 0; i < count; i++)
			answer[IntegerUtils.gcd(i, count)]++;
		if ((count & 1) == 1)
			answer[(count + 1) >> 1] += count;
		else {
			answer[count >> 1] += count >> 1;
			answer[(count >> 1) + 1] += count >> 1;
		}
		BigInteger result = BigInteger.ZERO;
		BigInteger exponent = BigInteger.ONE;
		for (int i = 0; i <= count; i++) {
			if (answer[i] != 0)
				result = result.add(exponent.multiply(BigInteger.valueOf(answer[i])));
			exponent = exponent.multiply(lBouquets);
		}
		out.printLine(result.divide(BigInteger.valueOf(2 * count)));
	}

	private long calculate(final int[] maxLength, final SimpleStringHash hash, final String sample, final int[] oOrder) {
		final int[] order = ArrayUtils.createOrder(maxLength.length);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				int length = Math.min(maxLength[first], maxLength[second]);
				if (hash.hash(first, first + length) != hash.hash(second, second + length))
					return oOrder[first] - oOrder[second];
				if (maxLength[first] == length) {
					if (maxLength[second] == length)
						return 0;
					return -1;
				}
				if (maxLength[second] == length)
					return 1;
				return sample.charAt(first + length) - sample.charAt(second + length);
			}
		});
		long answer = 0;
		int last = -1;
		for (int i : order) {
			if (last == -1)
				answer += maxLength[i];
			else
				answer += maxLength[i] - commonLength(last, i, maxLength, hash, sample);
			last = i;
		}
		return answer;
	}

	private int commonLength(int first, int second, int[] maxLength, SimpleStringHash hash, String sample) {
		int left = 0;
		int right = Math.min(maxLength[first], maxLength[second]);
		while (right - left > 0) {
			int middle = (left + right + 1) >> 1;
			if (hash.hash(first, first + middle) == hash.hash(second, second + middle))
				left = middle;
			else
				right = middle - 1;
		}
//		for (int i = left; i < right; i++) {
//			if (sample.charAt(first + i) != sample.charAt(second + i))
//				return i;
//		}
		return right;
	}

	static class SimpleStringHash {
		final int length;
		long[] hash;
		long[] reversePower;
		long reverse = BigInteger.valueOf(347).modInverse(BigInteger.ONE.shiftLeft(64)).longValue();

		public SimpleStringHash(String s) {
			length = s.length();
			hash = new long[length + 1];
			long power = 1;
			for (int i = 0; i < length; i++) {
				hash[i + 1] = hash[i] + s.charAt(i) * power;
				power *= 347;
			}
			reversePower = new long[length + 1];
			reversePower[0] = 1;
			for (int i = 1; i < length; i++)
				reversePower[i] = reversePower[i - 1] * reverse;
		}

		public final long hash(int from, int to) {
			return (hash[to] - hash[from]) * reversePower[from];
		}

	}
}
