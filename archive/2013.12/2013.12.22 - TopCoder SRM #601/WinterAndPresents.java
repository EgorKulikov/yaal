package net.egork;

public class WinterAndPresents {
    public long getNumber(int[] apple, int[] orange) {
		int min = Integer.MAX_VALUE;
		int count = apple.length;
		for (int i = 0; i < count; i++)
			min = Math.min(min, apple[i] + orange[i]);
		long answer = 0;
		for (int i = 1; i <= min; i++) {
			int maxApples = 0;
			int maxOranges = 0;
			for (int j = 0; j < count; j++) {
				maxApples += Math.min(i, apple[j]);
				maxOranges += Math.min(i, orange[j]);
			}
			answer += Math.max(maxApples + maxOranges - i * count + 1, 0);
		}
		return answer;
    }
}
