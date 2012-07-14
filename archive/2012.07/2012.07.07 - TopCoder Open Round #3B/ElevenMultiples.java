package net.egork;

import net.egork.collections.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class ElevenMultiples {
	static final long MOD = (long) (1e9 + 7);

	public int countMultiples(String[] pieces) {
		int oddCount = 0;
		for (String piece : pieces) {
			if (piece.length() % 2 == 1)
				oddCount++;
		}
		int[] oddValue = new int[oddCount];
		int[] evenValue = new int[pieces.length - oddCount];
		int oddIndex = 0;
		int evenIndex = 0;
		for (String piece : pieces) {
			int value = 0;
			for (int j = 0; j < piece.length(); j++) {
				if (j % 2 == 0)
					value += piece.charAt(j) - '0';
				else
					value -= piece.charAt(j) - '0';
			}
			value %= 11;
			value += 11;
			value %= 11;
			if (piece.length() % 2 == 1)
				oddValue[oddIndex++] = value;
			else
				evenValue[evenIndex++] = value;
		}
		long[][] oddVariants = count(oddValue);
		long[][] evenVariants = count(evenValue);
		long[][] c = IntegerUtils.generateBinomialCoefficients(200, MOD);
		long[] factorial = IntegerUtils.generateFactorial(200, MOD);
		long answer = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < evenVariants.length; j++) {
				for (int k = 0; k < 11; k++) {
					if ((i + k) % 11 != 0)
						continue;
					long current = oddVariants[oddCount / 2][i] * evenVariants[j][k] % MOD;
					if (current == 0)
						continue;
					current *= factorial[oddCount / 2];
					current %= MOD;
					current *= factorial[oddCount - oddCount / 2];
					current %= MOD;
					current *= factorial[j];
					current %= MOD;
					current *= factorial[evenVariants.length - j - 1];
					current %= MOD;
					if (j != 0 && oddCount == 0)
						continue;
					current *= c[j + (oddCount - 1) / 2][j];
					current %= MOD;
					current *= c[evenVariants.length - j - 1 + oddCount / 2][oddCount / 2];
					current %= MOD;
					answer += current;
				}
			}
		}
		return (int) (answer % MOD);
	}

	private long[][] count(int[] value) {
		long[][] result = new long[value.length + 1][11];
		long[][] next = new long[value.length + 1][11];
		result[0][0] = 1;
		for (int i : value) {
			ArrayUtils.fill(next, 0);
			for (int j = 0; j <= value.length; j++) {
				for (int k = 0; k < 11; k++) {
					if (result[j][k] == 0)
						continue;
					result[j][k] %= MOD;
					next[j][(k + i) % 11] += result[j][k];
					next[j + 1][(k + 11 - i) % 11] += result[j][k];
				}
			}
			long[][] temp = result;
			result = next;
			next = temp;
		}
		return result;
	}


}

