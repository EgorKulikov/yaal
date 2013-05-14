package on2012_10.on2012_10_10_Single_Round_Match_557.Incubator;



import net.egork.misc.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class Incubator {
	private long[] masks;
	Map<Long, Integer> answer;
	int[] order;

	public int maxMagicalGirls(String[] love) {
		int count = love.length;
		boolean[][] graph = new boolean[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++)
				graph[i][j] = love[i].charAt(j) == 'Y';
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++)
					graph[j][k] |= graph[j][i] && graph[i][k];
			}
		}
		masks = new long[count];
		long bad = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (graph[i][j])
					masks[i] += 1L << j;
			}
			if ((masks[i] >> i & 1) != 0) {
				masks[i] = -1;
				bad += 1L << i;
			} else
				masks[i] += 1L << i;
		}
		order = ArrayUtils.order(masks);
		ArrayUtils.reverse(order);
		answer = new HashMap<Long, Integer>();
		answer.put(0L, 0);
		return go(0, (1L << count) - 1 - bad);
	}

	private int go(int step, long mask) {
		if (answer.containsKey(mask))
			return answer.get(mask);
		int st = order[step];
		if ((mask >> st & 1) == 0)
			return go(step + 1, mask);
		if (Long.bitCount(masks[st]) <= 2) {
			int result = go(step + 1, mask & (~masks[st])) + 1;
			answer.put(mask, result);
			return result;
		}
		int result = Math.max(go(step + 1, mask - (1L << st)), 1 + go(step + 1, mask & (~masks[st])));
		answer.put(mask, result);
		return result;
	}
}
