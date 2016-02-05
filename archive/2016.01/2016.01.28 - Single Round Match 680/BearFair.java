package net.egork;

import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class BearFair {
	public String isFair(int n, int b, int[] upTo, int[] quantity) {
		upTo = Arrays.copyOf(upTo, upTo.length + 1);
		quantity = Arrays.copyOf(quantity, quantity.length + 1);
		upTo[upTo.length - 1] = b;
		quantity[quantity.length - 1] = n;
		ArrayUtils.orderBy(upTo, quantity);
		int exactOdd = 0;
		int exactEven = 0;
		int last = 0;
		int lastQ = 0;
		for (int i = 0; i < upTo.length; i++) {
			int delta = quantity[i] - lastQ;
			if (delta < 0) {
				return "unfair";
			}
			int odd = (upTo[i] + 1) / 2 - (last + 1) / 2;
			int even = upTo[i] / 2 - last / 2;
			if (delta > odd + even) {
				return "unfair";
			}
			exactOdd += Math.max(0, delta - even);
			exactEven += Math.max(0, delta - odd);
			last = upTo[i];
			lastQ = quantity[i];
		}
		if (exactOdd > n / 2 || exactEven > n / 2) {
			return "unfair";
		}
		return "fair";
	}
}
