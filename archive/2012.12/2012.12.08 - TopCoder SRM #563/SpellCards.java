package net.egork;

import java.util.Arrays;

public class SpellCards {
	public int maxDamage(int[] level, int[] damage) {
		int count = level.length;
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < count; i++) {
			int[] maxDamage = new int[count + 1];
			Arrays.fill(maxDamage, Integer.MIN_VALUE);
			maxDamage[0] = 0;
			for (int j = count - 1; j >= 0; j--) {
				int curCard = (i + j) % count;
				for (int k = count - 1; k >= 0; k--)
					maxDamage[k + 1] = maxDamage[k];
				maxDamage[0] = Integer.MIN_VALUE;
				for (int k = level[curCard]; k <= count; k++) {
					if (maxDamage[k] != Integer.MIN_VALUE)
						maxDamage[k - level[curCard]] = Math.max(maxDamage[k - level[curCard]], maxDamage[k] + damage[curCard]);
				}
			}
			for (int j : maxDamage)
				answer = Math.max(answer, j);
		}
		return answer;
	}
}
