package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class CielNumbersIII {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		char[] n = in.readString().toCharArray();
		int length = n.length;
		if (x > length) {
			out.printLine(-1);
			return;
		}
		int eights = 0;
		int fives = 0;
		int threes = 0;
		int other = 0;
		for (int i = 0; i < length; i++) {
			if (n[i] != '9' + 1 && can(x, eights, fives, threes, other, n[i] - '0', length - 1 - i)) {
				int eightsCopy = eights;
				int fivesCopy = fives;
				int threesCopy = threes;
				int otherCopy = other;
				switch (n[i] - '0') {
					case 8:
						eightsCopy++;
						break;
					case 5:
						fivesCopy++;
						break;
					case 3:
						threesCopy++;
						break;
					default:
						otherCopy++;
						break;
				}
				boolean good = false;
				boolean bad = false;
				for (int j = i + 1; j < length; j++) {
					boolean wasOther = false;
					for (int k = 0; k < n[j] - '0'; k++) {
						if (k != 3 && k != 5 && k != 8) {
							if (wasOther)
								continue;
							wasOther = true;
						}
						if (can(x, eightsCopy, fivesCopy, threesCopy, otherCopy, k, length - 1 - j)) {
							good = true;
							i = j - 1;
							eights = eightsCopy;
							fives = fivesCopy;
							threes = threesCopy;
							other = otherCopy;
							break;
						}
					}
					if (good)
						break;
					int digit = n[j] - '0';
					if (digit != 3 && digit != 5 && digit != 8 && wasOther) {
						bad = true;
						break;
					}
					if (!can(x, eightsCopy, fivesCopy, threesCopy, otherCopy, digit, length - 1 - j)) {
						bad = true;
						break;
					}
					if (n[j] - '0' == 8)
						eightsCopy++;
					else if (n[j] - '0' == 5)
						fivesCopy++;
					else if (n[j] - '0' == 3)
						threesCopy++;
					else
						otherCopy++;
				}
				if (good)
					continue;
				if (!bad)
					break;
			}
			boolean good = false;
			boolean wasOther = false;
			for (int k = n[i] - '0' - 1; k >= 0; k--) {
				if (k != 0 && k != 3 && k != 5 && k != 8) {
					if (wasOther)
						continue;
					wasOther = true;
				}
				if (can(x, eights, fives, threes, other, k, length - 1 - i)) {
					n[i] = (char) ('0' + k);
					good = true;
					if (k == 8)
						eights++;
					else if (k == 5)
						fives++;
					else if (k == 3)
						threes++;
					else if (k != 0 || eights != 0 || fives != 0 || threes != 0 || other != 0)
						other++;
					Arrays.fill(n, i + 1, length, (char) ('9' + 1));
					break;
				}
			}
			if (!good) {
				out.printLine(-1);
				return;
			}
		}
		for (int i = 0, nLength = n.length; i < nLength; i++) {
			char c = n[i];
			if (c != '0') {
				out.printLine(new String(n, i, length - i));
				return;
			}
		}
		out.printLine(-1);
	}

	private boolean can(int x, int eights, int fives, int threes, int other, int k, int remaining) {
		if (k == 8)
			eights++;
		else if (k == 5)
			fives++;
		else if (k == 3)
			threes++;
		else if (k != 0 || eights != 0 || fives != 0 || threes != 0 || other != 0)
			other++;
		int distribute = distribute(eights, fives, threes, other + remaining);
		x -= other + distribute;
		return x <= remaining && x >= 0;
	}

	private int distribute(int eights, int fives, int threes, int other) {
		if (fives >= threes) {
			eights += other;
			if (eights >= fives)
				return 0;
			int delta = Math.min((fives - eights + 1) >> 1, fives - threes);
			eights += delta;
			fives -= delta;
			if (eights >= fives)
				return delta;
			int delta2 = (fives - eights) / 3;
			eights += 2 * delta2;
			fives -= delta2;
			return delta + 2 * delta2 + fives - eights;
		}
		if (eights >= fives) {
			int delta = Math.min(Math.min(eights - fives, threes - fives), other);
			fives += delta;
			other -= delta;
			fives += other >> 1;
			eights += other - (other >> 1);
			if (fives >= threes)
				return 0;
			delta = Math.min((threes - fives + 1) >> 1, eights - fives);
			fives += delta;
			threes -= delta;
			if (fives >= threes)
				return delta;
			int delta2 = (threes - fives) / 3;
			fives += delta2;
			threes -= 2 * delta2;
			return delta + 2 * delta2 + threes - fives;
		}
		if (other >= 2 * threes - fives - eights)
			return 0;
		int total = threes + fives + eights + other;
		int fivesThird = (total + 1) / 3;
		if (fives <= fivesThird)
			return threes - total / 3;
		return fives - fivesThird + threes - total / 3;
	}
}
