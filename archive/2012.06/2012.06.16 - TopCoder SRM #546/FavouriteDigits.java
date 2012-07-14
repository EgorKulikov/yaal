package net.egork;

public class FavouriteDigits {
	public long findNext(long N, int digit1, int count1, int digit2, int count2) {
		long ten = 1;
		for (int i = 0; ; i++, ten *= 10) {
			if (i + 1 >= count1 + count2 && ten * 10 > N) {
				long result = go(i, N, count1, count2, digit1, digit2, ten, true);
				if (result != -1)
					return result;
			}
		}
	}

	private long go(int step, long n, int count1, int count2, int digit1, int digit2, long ten, boolean first) {
		if (count1 == 0 && count2 == 0)
			return n;
		if (step + 1 < count1 + count2)
			return -1;
		if (step == -1)
			throw new RuntimeException();
		long digit = n / ten;
		if (!first || digit != 0) {
			if (digit == digit1 && count1 != 0) {
				long result = go(step - 1, n % ten, count1 - 1, count2, digit1, digit2, ten / 10, false);
				if (result != -1)
					return result + digit * ten;
			}
			if (digit == digit2 && count2 != 0) {
				long result = go(step - 1, n % ten, count1, count2 - 1, digit1, digit2, ten / 10, false);
				if (result != -1)
					return result + digit * ten;
			}
			if (step >= count1 + count2) {
				long result = go(step - 1, n % ten, count1, count2, digit1, digit2, ten / 10, false);
				if (result != -1)
					return result + digit * ten;
			}
		}
		for (digit++; digit < 10; digit++) {
			long result = go(step - 1, Math.max(digit == digit1 ? count1 - 1 : count1, 0), Math.max(digit == digit2 ? count2 - 1 : count2, 0), digit1, digit2, ten / 10);
			if (result != -1)
				return result + digit * ten;
		}
		return -1;
	}

	private long go(int step, int count1, int count2, int digit1, int digit2, long ten) {
		if (step + 1 < count1 + count2)
			return -1;
		if (step == -1)
			return 0;
		if (step >= count1 + count2)
			return go(step - 1, count1, count2, digit1, digit2, ten / 10);
		if (digit1 < digit2 && count1 > 0)
			return go(step - 1, count1 - 1, count2, digit1, digit2, ten / 10) + digit1 * ten;
		if (digit1 > digit2 && count2 > 0)
			return go(step - 1, count1, count2 - 1, digit1, digit2, ten / 10) + digit2 * ten;
		if (count1 > 0)
			return go(step - 1, count1 - 1, count2, digit1, digit2, ten / 10) + digit1 * ten;
		if (count2 > 0)
			return go(step - 1, count1, count2 - 1, digit1, digit2, ten / 10) + digit2 * ten;
		throw new RuntimeException();
	}


}

