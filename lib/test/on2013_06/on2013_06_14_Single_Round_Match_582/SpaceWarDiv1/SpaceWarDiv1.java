package on2013_06.on2013_06_14_Single_Round_Match_582.SpaceWarDiv1;



import net.egork.misc.ArrayUtils;

import java.util.Arrays;

public class SpaceWarDiv1 {
    public long minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength, long[] enemyCount) {
		int count = magicalGirlStrength.length;
		Arrays.sort(magicalGirlStrength);
		ArrayUtils.reverse(magicalGirlStrength);
		int[] order = ArrayUtils.order(enemyStrength);
		ArrayUtils.reverse(order);
		long answer = 0;
		int j = 0;
		long current = 0;
		for (int i = 0; i < count; i++) {
			while (j < order.length && enemyStrength[order[j]] > magicalGirlStrength[i])
				current += enemyCount[order[j++]];
			if (i == 0 && current != 0)
				return -1;
			if (i != 0)
				answer = Math.max(answer, (current + i - 1) / i);
		}
		while (j < order.length)
			current += enemyCount[order[j++]];
		answer = Math.max(answer, (current + count - 1) / count);
		return answer;
	}
}
