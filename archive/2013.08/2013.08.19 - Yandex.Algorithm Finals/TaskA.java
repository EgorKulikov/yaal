package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskA {
	static final long FIRST = (long) (1e9 + 7);
	static final long SECOND = (long) (1e9 + 9);
	static final long MULTIPLIER = 103;
	static final long MASK = (1L << 32) - 1;

	static long[] firstPower = IntegerUtils.generatePowers(MULTIPLIER, 1000001, FIRST);
	static long[] secondPower = IntegerUtils.generatePowers(MULTIPLIER, 1000001, SECOND);
	static long[] firstReverse = IntegerUtils.generatePowers(BigInteger.valueOf(MULTIPLIER).modInverse(BigInteger.valueOf(FIRST)).longValue(), 1000001, FIRST);
	static long[] secondReverse = IntegerUtils.generatePowers(BigInteger.valueOf(MULTIPLIER).modInverse(BigInteger.valueOf(SECOND)).longValue(), 1000001, SECOND);

	static class Hash {
		long[] firstValue;
		long[] secondValue;
		char[] s;
		int length;

		Hash(char[] s) {
			this.s = s;
			length = s.length;
			firstValue = new long[s.length + 1];
			secondValue = new long[s.length + 1];
			for (int i = s.length - 1; i >= 0; i--) {
				int j = s.length - i;
				firstValue[j] = (firstValue[j - 1] + firstPower[j - 1] * s[i]) % FIRST;
				secondValue[j] = (secondValue[j - 1] + secondPower[j - 1] * s[i]) % SECOND;
			}
			ArrayUtils.reverse(this.s);
		}

		long hash(int from, int to) {
			to = length - to;
			from = length - from;
			long first = (firstValue[from] - firstValue[to] + FIRST) * firstReverse[to] % FIRST;
			long second = (secondValue[from] - secondValue[to] + SECOND) * secondReverse[to] % SECOND;
			return (first << 32) + second;
		}

		void add(char c) {
			if (firstValue.length == length + 1) {
				firstValue = Arrays.copyOf(firstValue, 2 * firstValue.length);
				secondValue = Arrays.copyOf(secondValue, 2 * secondValue.length);
				s = Arrays.copyOf(s, 2 * s.length + 1);
			}
			s[length++] = c;
			firstValue[length] = (firstValue[length - 1] + firstPower[length - 1] * c) % FIRST;
			secondValue[length] = (secondValue[length - 1] + secondPower[length - 1] * c) % SECOND;
		}

		char at(int position) {
			return s[length - position - 1];
		}
	}

	static class SubHash {
		Hash hash;
		int length;

		SubHash(Hash hash, int length) {
			this.length = length;
			this.hash = hash;
		}

		long hash(int from, int to) {
			return hash.hash(from, to);
		}

		char at(int position) {
			return hash.at(position);
		}
	}

	static class CompositeHash {
		private final SubHash left;
		private final Hash right;
		int length;

		CompositeHash(SubHash left, Hash right) {
			this.left = left;
			this.right = right;
			length = left.length + right.length;
		}

		long hash(int from, int to) {
			if (from >= left.length)
				return right.hash(from - left.length, to - left.length);
			if (to <= left.length)
				return left.hash(from, to);
			long leftResult = left.hash(from, left.length);
			long rightResult = right.hash(0, to - left.length);
			long leftFirst = leftResult >> 32;
			long leftSecond = leftResult & MASK;
			long rightFirst = rightResult >> 32;
			long rightSecond = rightResult & MASK;
			long first = (leftFirst * firstPower[to - left.length] + rightFirst) % FIRST;
			long second = (leftSecond * secondPower[to - left.length] + rightSecond) % SECOND;
			return (first << 32) + second;
		}

		char at(int position) {
			if (position < left.length)
				return left.at(position);
			return right.at(position - left.length);
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String input = in.readLine();
		String[] tokens = removeEmpty(input.split("[ ,-]+"));
		int length = tokens.length;
		String[] answer = new String[length];
		answer[length - 1] = tokens[length - 1].substring(0, 1);
		Hash hash = new Hash(answer[length - 1].toLowerCase().toCharArray());
		char next = Character.toLowerCase(answer[length - 1].charAt(0));
		for (int i = length - 2; i >= 0; i--) {
			Hash currentHash = new Hash(tokens[i].toLowerCase().toCharArray());
			int best = tokens[i].length();
			CompositeHash bestHash = new CompositeHash(new SubHash(currentHash, best), hash);
			for (int j = tokens[i].length() - 1; j > 0; j--) {
				char current = Character.toLowerCase(tokens[i].charAt(j));
				if (current < next)
					continue;
				CompositeHash leftHash = null;
				if (current == next) {
					int left = 0;
					leftHash = new CompositeHash(new SubHash(currentHash, j), hash);
					int right = leftHash.length;
					while (left < right) {
						int middle = (left + right + 1) >> 1;
						if (leftHash.hash(0, middle) == bestHash.hash(0, middle))
							left = middle;
						else
							right = middle - 1;
					}
					if (left != leftHash.length && leftHash.at(left) > bestHash.at(left))
						continue;
				}
				best = j;
				bestHash = leftHash == null ? new CompositeHash(new SubHash(currentHash, j), hash) : leftHash;
			}
			answer[i] = tokens[i].substring(0, best);
			for (int j = answer[i].length() - 1; j >= 0; j--)
				hash.add(Character.toLowerCase(answer[i].charAt(j)));
			next = Character.toLowerCase(answer[i].charAt(0));
		}
		int j = 0;
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			if (Character.isLetter(input.charAt(i)) && (i == 0 || !Character.isLetter(input.charAt(i - 1)))) {
				result.append(answer[j]);
				if (answer[j].length() < tokens[j].length())
					result.append('.');
				j++;
			}
			if (!Character.isLetter(input.charAt(i)))
				result.append(input.charAt(i));
		}
		out.printLine(result);
    }

	private String[] removeEmpty(String[] tokens) {
		int count = 0;
		for (String s : tokens) {
			if (!s.isEmpty())
				count++;
		}
		String[] result = new String[count];
		int i = 0;
		for (String s : tokens) {
			if (!s.isEmpty())
				result[i++] = s;
		}
		return result;
	}
}
