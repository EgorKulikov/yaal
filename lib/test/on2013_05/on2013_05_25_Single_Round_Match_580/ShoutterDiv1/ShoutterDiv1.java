package on2013_05.on2013_05_25_Single_Round_Match_580.ShoutterDiv1;



import net.egork.string.StringUtils;

import java.util.Arrays;

public class ShoutterDiv1 {
    public int count(String[] s1000, String[] s100, String[] s10, String[] s1, String[] t1000, String[] t100, String[] t10, String[] t1) {
		int[] start = build(s1000, s100, s10, s1);
		int[] end = build(t1000, t100, t10, t1);
		int count = start.length;
		int[] toLeft = new int[count];
		int[] toRight = new int[count];
		Arrays.fill(toLeft, -1);
		Arrays.fill(toRight, -1);
		int answer = 0;
		for (int i = 0; i < count; i++) {
			if (!findLeft(i, toLeft, start, end))
				return -1;
			if (!findRight(i, toRight, start, end))
				return -1;
			int current = toLeft[i] + toRight[i];
			for (int j = 0; j < count; j++) {
				if (start[j] < start[i] && end[j] > end[i]) {
					findLeft(j, toLeft, start, end);
					findRight(j, toRight, start, end);
					current = Math.min(current, 1 + toLeft[j] + toRight[j]);
				}
			}
			answer += current;
		}
		return answer;
    }

	private boolean findLeft(int at, int[] toLeft, int[] start, int[] end) {
		if (toLeft[at] != -1)
			return true;
		boolean good = true;
		int best = -1;
		int position = start[at];
		for (int i = 0; i < toLeft.length; i++) {
			if (end[i] < start[at])
				good = false;
			if (end[i] >= start[at] && start[i] < position) {
				best = i;
				position = start[i];
			}
		}
		if (good) {
			toLeft[at] = 0;
			return true;
		}
		if (best == -1)
			return false;
		boolean value = findLeft(best, toLeft, start, end);
		toLeft[at] = toLeft[best] + 1;
		return value;
	}

	private boolean findRight(int at, int[] toRight, int[] start, int[] end) {
		if (toRight[at] != -1)
			return true;
		boolean good = true;
		int best = -1;
		int position = end[at];
		for (int i = 0; i < toRight.length; i++) {
			if (start[i] > end[at])
				good = false;
			if (start[i] <= end[at] && end[i] > position) {
				best = i;
				position = end[i];
			}
		}
		if (good) {
			toRight[at] = 0;
			return true;
		}
		if (best == -1)
			return false;
		boolean value = findRight(best, toRight, start, end);
		toRight[at] = toRight[best] + 1;
		return value;
	}

	private int[] build(String[] s1000, String[] s100, String[] s10, String[] s1) {
		String d1000 = StringUtils.unite(s1000);
		String d100 = StringUtils.unite(s100);
		String d10 = StringUtils.unite(s10);
		String d1 = StringUtils.unite(s1);
		int[] result = new int[d1.length()];
		for (int i = 0; i < result.length; i++) {
			result[i] *= 10;
			result[i] += d1000.charAt(i) - '0';
			result[i] *= 10;
			result[i] += d100.charAt(i) - '0';
			result[i] *= 10;
			result[i] += d10.charAt(i) - '0';
			result[i] *= 10;
			result[i] += d1.charAt(i) - '0';
		}
		return result;
	}
}
