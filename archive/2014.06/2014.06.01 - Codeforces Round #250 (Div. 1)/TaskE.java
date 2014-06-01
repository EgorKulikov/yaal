package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	private static final long MOD = 998244353;
	private int rootPower = 1 << 23;
	private long root = 31;
	private long reverseRoot = IntegerUtils.reverse(root, MOD);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int upTo = in.readInt();
		int[] good = IOUtils.readIntArray(in, count);
		long[] base = new long[4 * upTo + 1];
		for (int i : good) {
			if (i <= upTo)
				base[i] = 1;
		}
		long[] answer = new long[4 * upTo + 1];
		answer[0] = 1;
		int STEP = 7000;
		long[] two = new long[4 * upTo + 1];
		long[] cTwo = new long[4 * upTo + 1];
		long[] next = new long[4 * upTo + 1];
		for (int i = 1; i <= upTo; i += STEP) {
			multiply(two, answer, answer, Math.min(i + STEP, upTo + 1));
			for (int j = 0; j < i; j++)
				cTwo[j] = two[j];
			multiply(next, cTwo, base, Math.min(i + STEP, upTo + 1));
			for (int j = i; j <= upTo && j < i + STEP; j++) {
				answer[j] = next[j];
				for (int k = j - 1; k >= i; k--) {
					if (base[j - k] == 1)
						answer[j] += two[k];
				}
				answer[j] %= MOD;
				for (int k = j; k <= upTo && k < i + STEP; k++) {
					if (k - j > j)
						break;
					if (k - j == j)
						two[k] += answer[j] * answer[j];
					else
						two[k] += 2 * answer[j] * answer[k - j];
					two[k] %= MOD;
				}
			}
		}
		for (int i = 0; i < upTo; i++) {
			out.printLine(answer[i + 1]);
		}
	}

	long[] aa;
	long[] bb;

	private void multiply(long[] result, long[] first, long[] second, int length) {
		if (aa == null) {
			aa = new long[result.length];
			bb = new long[result.length];
		}
		int resultSize = Integer.highestOneBit(length - 1) << 2;
		resultSize = Math.max(resultSize, 4);
		Arrays.fill(aa, 0, resultSize, 0);
		Arrays.fill(bb, 0, resultSize, 0);
		for (int i = 0; i < length; i++)
			aa[i] = first[i];
		for (int i = 0; i < length; i++)
			bb[i] = second[i];
		fft(aa, false, resultSize);
		if (first == second) {
			System.arraycopy(aa, 0, bb, 0, resultSize);
		} else
			fft(bb, false, resultSize);
		for (int i = 0; i < resultSize; i++) {
			aa[i] *= bb[i];
			aa[i] %= MOD;
		}
		fft(aa, true, resultSize);
		for (int i = 0; i < length; i++)
			result[i] = aa[i];

	}

	private void fft(long[] array, boolean invert, int size) {
		for (int i = 1, j = 0; i < size; ++i) {
			int bit = size >> 1;
			for (; j >= bit; bit >>= 1)
				j -= bit;
			j += bit;
			if (i < j) {
				long temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		for (int len = 2; len <= size; len <<= 1) {
			long wlen = invert ? reverseRoot : root;
			for (int i = len; i < rootPower; i <<= 1)
				wlen =	wlen * wlen % MOD;
			int half = len >> 1;
			for (int i = 0; i < size; i += len) {
				long w = 1;
				for (int j = 0; j < half; ++j) {
					long u = array[i + j], v = array[i + j + half] * w % MOD;
					array[i + j] = u + v < MOD ? u + v : u + v - MOD;
					array[i + j + half] = u - v >= 0 ? u - v : u - v + MOD;
					w =	w * wlen % MOD;
				}
			}
		}
		if (invert) {
			long reverseSize = IntegerUtils.reverse(size, MOD);
			for (int i = 0; i < size; ++i)
				array[i] = array[i] * reverseSize % MOD;
		}
	}
}
