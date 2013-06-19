package net.egork;

import java.util.Arrays;

public class TravelOnMars {
    public int minTimes(int[] range, int startCity, int endCity) {
		int count = range.length;
		int[] answer = new int[count];
		Arrays.fill(answer, Integer.MAX_VALUE);
		int[] queue = new int[count];
		queue[0] = startCity;
		int size = 1;
		answer[startCity] = 0;
		for (int i = 0; i < size; i++) {
			int c = queue[i];
			for (int j = 0; j < count; j++) {
				if (answer[j] == Integer.MAX_VALUE && (Math.abs(c - j) <= range[c] || count - Math.abs(c - j) <= range[c])) {
					queue[size++] = j;
					answer[j] = answer[c] + 1;
				}
			}
		}
		return answer[endCity];
    }
}
