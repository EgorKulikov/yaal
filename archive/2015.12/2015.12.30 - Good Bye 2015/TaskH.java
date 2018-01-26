package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.misc.ArrayUtils;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

public class TaskH {
	int[] bl;
	int[] nonBase;
	int digits;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		digits = Integer.toString(n).length();
		int[][] qty = new int[digits][digits];
		for (int i = 0; i < n - 1; i++) {
			int a = in.readString().length() - 1;
			int b = in.readString().length() - 1;
			qty[Math.min(a, b)][Math.max(a, b)]++;
		}
		nonBase = ArrayUtils.createArray(digits, -1);
		for (int i = 1; i <= n; i++) {
			nonBase[Integer.toString(i).length() - 1]++;
		}
		int[] f = new int[digits * (digits - 1) / 2];
		int[] t = new int[f.length];
		int at = 0;
		for (int i = 0; i < digits; i++) {
			for (int j = 0; j < i; j++) {
				f[at] = j;
				t[at++] = i;
			}
		}
		for (int i = 0; i < (1 << f.length); i++) {
			if (Integer.bitCount(i) != digits - 1) {
				continue;
			}
			IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(digits);
			boolean good = true;
			for (int j = 0; j < f.length; j++) {
				if ((i >> j & 1) == 1) {
					setSystem.join(f[j], t[j]);
					qty[f[j]][t[j]]--;
					if (qty[f[j]][t[j]] < 0) {
						good = false;
					}
				}
			}
			if (setSystem.getSetCount() != 1 || !good) {
				for (int j = 0; j < f.length; j++) {
					if ((i >> j & 1) == 1) {
						qty[f[j]][t[j]]++;
					}
				}
				continue;
			}
			good = true;
			bl = new int[1 << digits];
			for (int j = 1; j < (1 << digits); j++) {
				int balance = 0;
				for (int k = 0; k < digits; k++) {
					if ((j >> k & 1) == 0) {
						continue;
					}
					balance -= nonBase[k];
					for (int l = 0; l < digits; l++) {
						balance += qty[k][l];
						if ((j >> l & 1) == 0) {
							balance += qty[l][k];
						}
					}
				}
				bl[j] = balance;
				if (balance < 0) {
					good = false;
					break;
				}
			}
			if (!good) {
				for (int j = 0; j < f.length; j++) {
					if ((i >> j & 1) == 1) {
						qty[f[j]][t[j]]++;
					}
				}
				continue;
			}
			int[] base = new int[digits];
			int[] next = new int[digits];
			int c = 1;
			for (int j = 0; j < digits; j++) {
				base[j] = c;
				next[j] = c + 1;
				c *= 10;
			}
			int remaining = n - 1;
			for (int j = 0; j < f.length; j++) {
				if ((i >> j & 1) == 1) {
					out.printLine(base[f[j]], base[t[j]]);
					remaining--;
				}
			}
			boolean[] spotted = new boolean[1 << digits];
			while (remaining > 0) {
				boolean found = false;
				for (int j = 1; j < (1 << digits); j++) {
					if (bl[j] == 0 && !spotted[j]) {
						spotted[j] = true;
						for (int k = 0; k < digits; k++) {
							if ((j >> k & 1) == 0) {
								continue;
							}
							for (int l = 0; l < digits; l++) {
								if ((j >> l & 1) == 1) {
									continue;
								}
								for (int m = 0; m < qty[k][l] + qty[l][k]; m++) {
									out.printLine(base[l], next[k]++);
									remaining--;
									addEdge(k, l);
								}
								qty[k][l] = qty[l][k] = 0;
							}
						}
						found = true;
						break;
					}
				}
				if (found) {
					continue;
				}
				for (int j = 0; !found && j < digits; j++) {
					for (int k = 0; k <= j; k++) {
						if (qty[k][j] != 0) {
							found = true;
							out.printLine(base[j], next[k]++);
							addEdge(k, j);
							qty[k][j]--;
							remaining--;
							break;
						}
					}
				}
			}
			return;
		}
		out.printLine(-1);
	}

	private void addEdge(int next, int base) {
		nonBase[next]--;
		for (int i = 0; i < (1 << digits); i++) {
			if ((i >> base & 1) == 1 && (i >> next & 1) == 0) {
				bl[i]--;
			}
		}
	}
}
