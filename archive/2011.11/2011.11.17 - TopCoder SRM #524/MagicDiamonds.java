package net.egork;

import net.egork.numbers.IntegerUtils;

public class MagicDiamonds {
	public long minimalTransfer(long n) {
		if (!IntegerUtils.isPrime(n))
			return 1;
		if (n == 3)
			return 3;
		return 2;
	}

}

