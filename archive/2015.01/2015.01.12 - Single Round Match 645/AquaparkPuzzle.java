package net.egork;

import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;

public class AquaparkPuzzle {
	static final long MOD = (long) (1e9 + 7);

	int[][] ways;
	boolean[] valid;
	int[] qty;
	int k;
	int count;

    public int countSchedules(int k, int m, int[] c) {
		this.k = k;
		count = c.length;
		valid = new boolean[1 << count];
		for (int i = 0; i < valid.length; i++) {
			int cost = 0;
			for (int j = 0; j < count; j++) {
				if ((i >> j & 1) == 1) cost += c[j];
			}
			valid[i] = cost <= m;
		}
		ways = new int[count + 1][(int) IntegerUtils.power(3, count)];
		ArrayUtils.fill(ways, -1);
		qty = new int[valid.length];
		for (int i = 0; i < valid.length; i++) {
			for (int j = i; j > 0; j = (j - 1) & i) qty[i] += valid[j] ? 1 : 0;
			qty[i]++;
		}
		long answer = 0;
		for (int i = 0; i < valid.length; i++) {
			long current = go(0, i, 0);
			if ((Integer.bitCount(i) & 1) == 0) answer += current;
			else answer -= current;
		}
		answer %= MOD;
		if (answer < 0) answer += MOD;
		return (int) answer;
    }

	private int go(int moves, int mayOnce, int forbidden) {
		int key = 0;
		for (int i = 0; i < count; i++) {
			key *= 3;
			if ((mayOnce >> i & 1) == 1) key++;
			else if ((forbidden >> i & 1) == 1) key += 2;
		}
		if (ways[moves][key] != -1) return ways[moves][key];
		if (moves > k) return ways[moves][key] = 0;
		if (mayOnce != 0) {
			int fixed = Integer.lowestOneBit(mayOnce);
			int remaining = qty.length - 1 - forbidden - fixed;
			long delta = 0;
			for (int j = remaining; j > 0; j = (j - 1) & remaining) {
				if (valid[j + fixed]) delta += go(moves + 1, mayOnce & (~(j + fixed)), forbidden | (mayOnce & (j + fixed)));
			}
			if (valid[fixed]) delta += go(moves + 1, mayOnce - fixed, forbidden + fixed);
			delta %= MOD;
			ways[moves][key] = (int) ((delta * (k - moves) + go(moves, mayOnce - fixed, forbidden + fixed)) % MOD);
		} else {
			ways[moves][key] = (int) IntegerUtils.power(qty[qty.length - 1 - forbidden], k - moves, MOD);
		}
		return ways[moves][key];
	}
}
