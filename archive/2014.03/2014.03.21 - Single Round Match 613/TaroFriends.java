package net.egork;

import java.util.Arrays;

public class TaroFriends {
    public int getNumber(int[] coordinates, int X) {
		Arrays.sort(coordinates);
		int count = coordinates.length;
		int answer = coordinates[count - 1] - coordinates[0];
		for (int i = 1; i < count; i++) {
			int leftmost = Math.min(coordinates[0] + X, coordinates[i] - X);
			int rightmost = Math.max(coordinates[i - 1] + X, coordinates[count - 1] - X);
			answer = Math.min(answer, rightmost - leftmost);
		}
		return answer;
    }
}
