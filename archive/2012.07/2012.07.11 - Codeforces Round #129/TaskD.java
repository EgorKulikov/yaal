package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int subStringLength = in.readInt();
		if (2 * subStringLength > length) {
			out.printLine(0);
			return;
		}
		char[] s = new char[length];
		for (int i = 0; i < length; i++)
			s[i] = in.readCharacter();
		long[] countDirect = count(s, length, subStringLength, 'B');
		char[] reverse = new char[length];
		for (int i = 0; i < length; i++)
			reverse[i] = s[length - i - 1];
		long[] countReverse = count(reverse, length, subStringLength, 'W');
		long answer = 0;
		long sum = 0;
		for (int i = 0; i + 2 * subStringLength <= length; i++) {
			if (s[i + subStringLength - 1] == 'X') {
				sum *= 2;
				if (sum >= MOD)
					sum -= MOD;
			}
			sum += countDirect[i];
			if (sum >= MOD)
				sum -= MOD;
			answer += sum * countReverse[length - i - 2 * subStringLength] % MOD;
		}
		answer %= MOD;
		out.printLine(answer);
	}

	private long[] count(char[] s, int length, int subStringLength, char good) {
		int countBad = 0;
		long[] count = new long[length];
		long[] result = new long[length];
		if (s[0] == 'X') {
			count[0] = 2;
		} else {
			count[0] = 1;
			if (s[0] != good)
				countBad++;
		}
		for (int i = 1; i < subStringLength; i++) {
			if (s[i] == 'X') {
				count[i] = count[i - 1] * 2;
				if (count[i] >= MOD)
					count[i] -= MOD;
			} else {
				count[i] = count[i - 1];
				if (s[i] != good)
					countBad++;
			}
		}
		if (countBad == 0) {
			result[0] = 1;
			count[subStringLength - 1]--;
		}
		for (int i = subStringLength; i < length; i++) {
			if (s[i] == 'X') {
				count[i] = count[i - 1] * 2;
				if (count[i] >= MOD)
					count[i] -= MOD;
			} else {
				count[i] = count[i - 1];
				if (s[i] != good)
				countBad++;
			}
			if (s[i - subStringLength] != 'X' && s[i - subStringLength] != good)
				countBad--;
			if (countBad == 0 && s[i - subStringLength] != good) {
				result[i - subStringLength + 1] = (i == subStringLength ? 1 : count[i - subStringLength - 1]);
				count[i] -= result[i - subStringLength + 1];
				if (count[i] < 0)
					count[i] += MOD;
			}
		}
		return result;
	}
}
