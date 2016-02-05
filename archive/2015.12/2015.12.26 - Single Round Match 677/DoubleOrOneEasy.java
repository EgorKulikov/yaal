package net.egork;

public class DoubleOrOneEasy {
	public int minimalSteps(int a, int b, int newA, int newB) {
		if (a > b) {
			return minimalSteps(b, a, newB, newA);
		}
		int startDelta = b - a;
		int endDelta = newB - newA;
		if (startDelta == 0) {
			if (endDelta != 0 || a > newA) {
				return -1;
			}
			int answer = 0;
			while (a * 2 <= newA) {
				if ((newA % 2) == 1) {
					newA--;
					answer++;
				}
				newA /= 2;
				answer++;
			}
			answer += newA - a;
			return answer;
		}
		if (endDelta <= 0) {
			return -1;
		}
		if (endDelta % startDelta != 0) {
			return -1;
		}
		int ratio = endDelta / startDelta;
		if (Integer.bitCount(ratio) != 1) {
			return -1;
		}
		if (newA / a < ratio) {
			return -1;
		}
		int answer = 0;
		while (ratio > 1) {
			if ((newA % 2) == 1) {
				newA--;
				answer++;
			}
			newA /= 2;
			answer++;
			ratio /= 2;
		}
		answer += newA - a;
		return answer;
	}
}
