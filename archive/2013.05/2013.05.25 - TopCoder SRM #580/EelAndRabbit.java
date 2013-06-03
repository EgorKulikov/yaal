package net.egork;

public class EelAndRabbit {
    public int getmax(int[] l, int[] t) {
		int count = l.length;
		int answer = 1;
		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				int current = 0;
				for (int k = 0; k < count; k++) {
					if (t[i] >= t[k] && t[i] <= t[k] + l[k] || t[j] >= t[k] && t[j] <= t[k] + l[k])
						current++;
				}
				answer = Math.max(answer, current);
			}
		}
		return answer;
    }
}
