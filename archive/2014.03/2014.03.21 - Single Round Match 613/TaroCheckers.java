package net.egork;

import net.egork.misc.ArrayUtils;

public class TaroCheckers {
	private static final long MOD = (long) (1e9 + 7);
	int[] events;
	long[][][] answer;
	int count;
	
    public int getNumber(int[] left, int[] right, int M) {
		count = left.length;
		events = new int[M + 2 * count];
		int at = 0;
		for (int i = 1; i <= M; i++) {
			at++;
			for (int j = 0; j < count; j++) {
				if (left[j] == i)
					events[at++] = -1;
				if (right[j] == M - i)
					events[at++] = 1;
			}
		}
		answer = new long[events.length][count + 1][count + 1];
		ArrayUtils.fill(answer, -1);
		return (int)go(0, 0, 0, count, 0, 0);
    }

	private long go(int step, int inLeft, int inRight, int left, int free, int right) {
		if (step == events.length)
			return inLeft == 0 && inRight == count ? 1 : 0;
		if (answer[step][inLeft][inRight] != -1)
			return answer[step][inLeft][inRight];
		answer[step][inLeft][inRight] = 0;
		if (events[step] == 0) {
			if (left > inLeft)
				answer[step][inLeft][inRight] += go(step + 1, inLeft + 1, inRight, left, free, right);
			answer[step][inLeft][inRight] += (free + 1) * go(step + 1, inLeft, inRight, left, free, right);
			if (right > inRight)
				answer[step][inLeft][inRight] += (right - inRight) * go(step + 1, inLeft, inRight + 1, left, free, right);
		} else if (events[step] == 1)
			answer[step][inLeft][inRight] = go(step + 1, inLeft, inRight, left, free - 1, right + 1);
		else {
			if (inLeft != 0)
				answer[step][inLeft][inRight] += inLeft * go(step + 1, inLeft - 1, inRight, left - 1, free + 1, right);
		}
		return answer[step][inLeft][inRight] %= MOD;
	}
}
