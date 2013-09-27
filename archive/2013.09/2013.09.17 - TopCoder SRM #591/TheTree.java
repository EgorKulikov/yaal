package net.egork;

public class TheTree {
    public int maximumDiameter(int[] cnt) {
		int d = cnt.length;
		int answer = d;
		for (int i = 0; i < d; i++) {
			for (int j = i; j < d; j++) {
				if (cnt[j] == 1)
					break;
				answer = Math.max(answer, j - i + d - i + 1);
			}
		}
		return answer;
    }
}
