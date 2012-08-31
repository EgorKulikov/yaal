package on2012_07.on2012_6_22.checkerexpansion;



import net.egork.collections.Pair;

import java.util.HashMap;
import java.util.Map;

public class CheckerExpansion {
	Map<Pair<Long, Long>, Character> map = new HashMap<Pair<Long, Long>, Character>();

	public String[] resultAfter(long t, long x0, long y0, int w, int h) {
		map.put(Pair.makePair(0L, 0L), 'A');
		map.put(Pair.makePair(1L, 0L), '.');
		map.put(Pair.makePair(2L, 0L), 'B');
		map.put(Pair.makePair(3L, 0L), '.');
		map.put(Pair.makePair(0L, 1L), '.');
		map.put(Pair.makePair(1L, 1L), 'B');
		map.put(Pair.makePair(2L, 1L), '.');
		map.put(Pair.makePair(3L, 1L), '.');
		char[][] result = new char[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				result[i][j] = x0 + y0 + i + j > 2 * (t - 1) ? '.' : get(x0 + j, y0 + i);
		}
		String[] answer = new String[h];
		for (int i = 0; i < h; i++)
			answer[i] = new String(result[h - i - 1]);
		return answer;
	}

	private char get(long x, long y) {
		if (y > x)
			return '.';
		Pair<Long, Long> key = Pair.makePair(x, y);
		if (map.containsKey(key))
			return map.get(key);
		long xx = Long.highestOneBit(x);
		char result;
		if (y < (xx >> 1))
			result = get(x - xx, y);
		else if (y < xx && x < xx + (xx >> 1))
			result = get(x - (xx >> 1), y - (xx >> 1));
		else if (y < xx)
			result = '.';
		else
			result = get(x - xx, y - xx);
		map.put(key, result);
		return result;
	}


}

