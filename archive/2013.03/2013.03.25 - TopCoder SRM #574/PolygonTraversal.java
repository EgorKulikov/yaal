package net.egork;

import net.egork.collections.ArrayUtils;

public class PolygonTraversal {
	long[][] answer;

    public long count(int N, int[] points) {
		answer = new long[N][1 << N];
		int start = 0;
		for (int i : points)
			start += 1 << (i - 1);
		ArrayUtils.fill(answer, -1);
		return go(points[points.length - 1] - 1, points[0] - 1, start);
    }

	private long go(int current, int first, int mask) {
		if (answer[current][mask] != -1)
			return answer[current][mask];
		answer[current][mask] = 0;
		if (mask == (1 << answer.length) - 1) {
			if (intersect(current, first, mask))
				answer[current][mask] = 1;
		}
		for (int i = 0; i < answer.length; i++) {
			if ((mask >> i & 1) == 0 && intersect(current, i, mask))
				answer[current][mask] += go(i, first, mask + (1 << i));
		}
		return answer[current][mask];
	}

	private boolean intersect(int from, int to, int mask) {
		if (from > to) {
			int temp = from;
			from = to;
			to = temp;
		}
		boolean inner = false;
		boolean outer = false;
		for (int i = 0; i < answer.length; i++) {
			if ((mask >> i & 1) == 1) {
				if (i < from || i > to)
					outer = true;
				else if (i > from && i < to)
					inner = true;
			}
		}
		return outer && inner;
	}
}
